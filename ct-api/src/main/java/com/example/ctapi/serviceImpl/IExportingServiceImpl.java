package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.*;
import com.example.ctapi.mappers.IExportingbillMapper;
import com.example.ctapi.mappers.IExportingbillTransactionMapper;
import com.example.ctapi.services.IExportingbillService;
import com.example.ctcommon.enums.BillStatus;
import com.example.ctcommon.enums.TypeBillRealTime;
import com.example.ctcommondal.entity.ExportbillEntity;
import com.example.ctcommondal.entity.ExportingBillTransactionEntity;
import com.example.ctcommondal.repository.IExportingTransactionRepository;
import com.example.ctcommondal.repository.IExportingbillRepository;
import com.example.ctcoremodel.CustomerModel;
import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.ResponseModel;
import com.example.ctcoreservice.services.IWarehouseRequestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IExportingServiceImpl implements IExportingbillService {
    private final IExportingbillRepository iExportingbillRepository;
    private final IExportingTransactionRepository iExportingTransactionRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final IWarehouseRequestService warehouseRequestService;
    private final Logger logger = LoggerFactory.getLogger(IExportingServiceImpl.class);

    @Override
    public void createExportingbill(ExportingBillFullDto exportingBillFullDto) {
        try {
            // thêm vào thông tin chung của đơn hàng
            exportingBillFullDto.getExportingBill().setStatus(BillStatus.BOOKING);

            ExportingBillDto tempEx = exportingBillFullDto.getExportingBill();
            if (tempEx.getCustomer() == null) {

                CustomerModel customer=new  CustomerModel();
                tempEx.setCustomer(customer);
            }
            ExportbillEntity exportbillEntity = IExportingbillMapper.INSTANCE.toFromExportingbillDto(exportingBillFullDto.getExportingBill());
            iExportingbillRepository.save(exportbillEntity);
            // thêm vào chi tiet don hang
            for (ExportingBillTransactionDto detail : exportingBillFullDto.getExportingBillTransactions()) {
                detail.setBill(exportingBillFullDto.getExportingBill());
            }

            List<ExportingBillTransactionEntity> exportingBillTransactionEntity = IExportingbillTransactionMapper.
                    INSTANCE.toListFromExportingbillTransactionDto(exportingBillFullDto.getExportingBillTransactions());

            for (ExportingBillTransactionEntity detail : exportingBillTransactionEntity) {
                iExportingTransactionRepository.save(detail);
            }

            messagingTemplate.convertAndSend("/topic/" + "billRealTimeSection",
                    new SocketMessage("billRealTimeSection", TypeBillRealTime.BOOKING.toString(), exportingBillFullDto));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public ExportingBillFullSearchDto getAllExportingBillUseBaseSearch(HttpServletRequest request) throws IOException {
        int a = 0;
        try {
            List<ExportbillEntity> exporting = iExportingbillRepository.findAll();
            List<ExportingBillDto> bill = IExportingbillMapper.INSTANCE.toFromExportingbillDtoList(exporting);
            List<String> billId = bill.stream().map(ExportingBillDto::getId).collect(Collectors.toList());
            List<ExportingBillTransactionEntity> exportTransaction = iExportingTransactionRepository.getAllDetails(billId);
            List<ExportingBillTransactionDto> transactions = IExportingbillTransactionMapper.INSTANCE.toExportingBillTransactionDtoList(exportTransaction);
            List<String> customerId = bill.stream().map(ExportingBillDto::getCustomer).map(CustomerModel::getId).collect(Collectors.toList());

            ResponseModel<List<CustomerModel>> reponeFromWareHouseCustomer = warehouseRequestService
                    .getCustomerModelFromWarehouseByIds(request, customerId);
            List<CustomerModel> customerModels = reponeFromWareHouseCustomer != null ? reponeFromWareHouseCustomer.getResult() : new ArrayList<>();

            List<String> productIds = transactions.stream()
                    .map(ExportingBillTransactionDto::getProduct).map(ProductModel::getId).distinct()
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> responseFromWareHouse = productIds.size() > 0 ? warehouseRequestService
                    .getAllProductModelFromWarehouseByIds(request, productIds) : null;

            List<ProductModel> productModels = responseFromWareHouse != null ? responseFromWareHouse.getResult() : new ArrayList<>();

            for (ExportingBillDto e : bill) {
                CustomerModel customerModel = customerModels.stream().filter(customerModel1 -> customerModel1.getId().equals(e.getCustomer().getId()))
                        .findFirst().orElse(null);
                e.setCustomer(customerModel);
            }
            List<ExportingBillFullDto> exportingBillFullDtos = new ArrayList<>();

            for (int i = 0; i < Math.min(transactions.size(), bill.size()); i++) {

                ExportingBillTransactionDto transaction = transactions.get(i);
                ExportingBillDto billDto = bill.get(i);
                ExportingBillFullDto exportingBillFullDto = new ExportingBillFullDto();
                exportingBillFullDto.setExportingBill(billDto);
                ProductModel productOfDetail = productModels
                        .stream().filter(product -> product.getId().equals(transaction.getProduct().getId())).findFirst().orElse(null);
                transaction.setProduct(productOfDetail);
                List<ExportingBillTransactionDto> transactionList = new ArrayList<>();
                transactionList.add(transaction);
                exportingBillFullDto.setExportingBillTransactions(transactionList);
                exportingBillFullDtos.add(exportingBillFullDto);
            }


            ExportingBillFullSearchDto result = new ExportingBillFullSearchDto();
            result.setResult(exportingBillFullDtos);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<ExportingBillFullDto> getAllExportingbill(HttpServletRequest request) throws IOException {
        List<ExportbillEntity> exportbillEntities = this.iExportingbillRepository.getAllBill();
        List<ExportingBillDto> exportingBillDtos = IExportingbillMapper.INSTANCE.toFromExportingbillDtoList(exportbillEntities);

        List<String> ids = exportingBillDtos.stream().map(ExportingBillDto::getId).collect(Collectors.toList());

        List<ExportingBillTransactionEntity> exportingBillTransactionEntities = this.iExportingTransactionRepository.getAllDetails(ids);
        List<ExportingBillTransactionDto> exportingBillTransactionDtos = IExportingbillTransactionMapper.
                INSTANCE.toExportingBillTransactionDtoList(exportingBillTransactionEntities);

        //lấy ra hết ids sản phẩm để gọi qua warehouse lấy full thông tin
        List<String> productIds = exportingBillTransactionDtos.stream()
                .map(ExportingBillTransactionDto::getProduct).map(ProductModel::getId).distinct()
                .collect(Collectors.toList());

        ResponseModel<List<ProductModel>> responseFromWareHouse = productIds.size() > 0 ? warehouseRequestService
                .getAllProductModelFromWarehouseByIds(request, productIds) : null;

        List<ProductModel> productModels = responseFromWareHouse != null ? responseFromWareHouse.getResult() : new ArrayList<>();

        // duyệt qua từng hóa đơn đặt hàng
        List<ExportingBillFullDto> exportingBillFullDtos = new ArrayList<>();
        for (ExportingBillDto e : exportingBillDtos) {
            ExportingBillFullDto export = new ExportingBillFullDto();
            export.setExportingBill(e);
            //lấy hết tất cả chi tiết
            List<ExportingBillTransactionDto> details = exportingBillTransactionDtos
                    .stream().filter(detail -> detail.getBill().getId().equals(e.getId())).collect(Collectors.toList());
            for (ExportingBillTransactionDto detail : details) {
                detail.setBill(null);
                ProductModel productOfDetail = productModels
                        .stream().filter(product -> product.getId().equals(detail.getProduct().getId())).findFirst().orElse(null);
                detail.setProduct(productOfDetail);
            }
            export.setExportingBillTransactions(details);
            exportingBillTransactionDtos.removeAll(details);
            exportingBillFullDtos.add(export);
        }

        return exportingBillFullDtos;
    }

    @Override
    public List<ProductModel> getTesTToWarehouse(HttpServletRequest request) throws IOException {
        List<String> ids = List.of("00474ba4-da19-43a6-b980-9e2b439e992e");
        ResponseModel<List<ProductModel>> rs = warehouseRequestService.getAllProductModelFromWarehouseByIds(request, ids);
        return rs.getResult();
    }
}

