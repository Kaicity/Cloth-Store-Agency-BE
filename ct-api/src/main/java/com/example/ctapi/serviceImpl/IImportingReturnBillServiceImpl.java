package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.*;
import com.example.ctapi.mappers.*;
import com.example.ctapi.services.IImportingReturnService;
import com.example.ctcommon.enums.BillStatus;
import com.example.ctcommon.enums.TypeBillRealTime;
import com.example.ctcommondal.entity.*;
import com.example.ctcommondal.repository.IExportingTransactionRepository;
import com.example.ctcommondal.repository.IExportingbillRepository;
import com.example.ctcommondal.repository.IImportingReturnBIllRepository;
import com.example.ctcommondal.repository.IImportingReturnTransactionRepository;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IImportingReturnBillServiceImpl implements IImportingReturnService {
    private final IImportingReturnBIllRepository iImportingReturnBIllRepository;
    private final IImportingReturnTransactionRepository iImportingReturnTransactionRepository;
    private final IExportingbillRepository iExportingbillRepository;
    private final IExportingTransactionRepository iExportingTransactionRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final IWarehouseRequestService warehouseRequestService;
    private final Logger logger = LoggerFactory.getLogger(IExportingServiceImpl.class);

    @Transactional
    @Override
    public void createImportingReturnbill(ImportingReturnBillFullDto importingReturnBIll) {
        try {
            String importId = importingReturnBIll.getImportbill().getExporting().getId();
            List<ExportingBillTransactionEntity> listTransanOld = iExportingTransactionRepository.findTransactionbyId(importId);
            List<ExportingBillTransactionDto> listTransactionDto = IExportingbillTransactionMapper
                    .INSTANCE.toExportingBillTransactionDtoList(listTransanOld);

            // Kiểm tra xem danh sách giao dịch nhập có rỗng hay không
            if (!listTransactionDto.isEmpty()) {
                for (ImportingReturnBillTransactionDto transaction : importingReturnBIll
                        .getImportingTransactions()) {
                    String id = transaction.getProduct().getId();
                    int quantity = transaction.getQuantity();

                    boolean isMatch = false;
                    // Duyệt qua từng giao dịch nhập để kiểm tra
                    for (ExportingBillTransactionDto ExportingTransaction : listTransactionDto) {
                        if (ExportingTransaction.getProduct().equals(id) && quantity <= ExportingTransaction.getQuantity()) {
                            isMatch = true;
                            break;
                        }
                    }

                    // Nếu không tìm thấy giao dịch nhập phù hợp, bỏ qua và tiếp tục với giao dịch kế tiếp
                    if (!isMatch) {
                        continue;
                    }
                    // thêm vào thông tin chung của đơn hàng
                    importingReturnBIll.getImportbill().setStatus(BillStatus.BOOKING);

                    ImportingReturnBIllDto tempEx = importingReturnBIll.getImportbill();
                    if (tempEx.getCustomer() == null) {

                        CustomerModel customer = new CustomerModel();
                        tempEx.setCustomer(customer);
                    }
                    ImportingReturnbillEntity importreturnbillEntity = IImportingReturnBillMapper.INSTANCE.toFromExportingReturnbillEntity(importingReturnBIll.getImportbill());
                    iImportingReturnBIllRepository.save(importreturnbillEntity);
                    // thêm vào chi tiet don hang
                    for (ImportingReturnBillTransactionDto detail : importingReturnBIll.getImportingTransactions()) {
                        detail.setBill(importingReturnBIll.getImportbill());
                    }

                    List<ImportingBillReturnTransactionEntity> importingReturnBillTransactionEntity = IImportingReturnBIllTransactionMapper.
                            INSTANCE.toFromImportingReturnbillTransactionsDto(importingReturnBIll.getImportingTransactions());

                    for (ImportingBillReturnTransactionEntity detail : importingReturnBillTransactionEntity) {
                        iImportingReturnTransactionRepository.save(detail);
                    }

                    messagingTemplate.convertAndSend("/topic/" + "billRealTimeSection",
                            new SocketMessage("billRealTimeSection", TypeBillRealTime.BOOKING.toString(), importingReturnBIll));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public ImportingReturnBillFullDto getImportingReturnById(HttpServletRequest request, String id) throws IOException {

        try {
            ImportingReturnbillEntity ExportingEntity = iImportingReturnBIllRepository.findImportingById(id);
            ImportingReturnBIllDto exportingDto = IImportingReturnBillMapper
                    .INSTANCE.toFromImportingReturnbillDto(ExportingEntity);

            //Trả về danh sách id supplier theo importing
            List<String> importIds = new ArrayList<>();
            importIds.add(exportingDto.getCustomer().getId());

            String customerId = exportingDto.getCustomer().getId();
            List<String> customerIds = new ArrayList<>();
            customerIds.add(customerId);

            ResponseModel<List<CustomerModel>> reponeFromWareHouseCustomer = warehouseRequestService
                    .getCustomerModelFromWarehouseByIds(request, customerIds);
            List<CustomerModel> customerModels = reponeFromWareHouseCustomer != null ? reponeFromWareHouseCustomer.getResult() : new ArrayList<>();
            if (customerModels.size() == 0) exportingDto.setCustomer(null);
            else exportingDto.setCustomer(customerModels.get(0));

            List<ImportingBillReturnTransactionEntity> ExportingTransactionEntities = iImportingReturnTransactionRepository.findTransactionbyId(id);
            List<ImportingReturnBillTransactionDto> ExportingTransactionDtos = IImportingReturnBIllTransactionMapper
                    .INSTANCE.toImportingReturnBillTransactionsDto(ExportingTransactionEntities);

            //Trả về danh sách các product có trong importingTransaction
            List<String> TransactionIds = ExportingTransactionDtos.stream()
                    .map(ImportingReturnBillTransactionDto::getProduct)
                    .map(ProductModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> responseFromWareHouseProduct = warehouseRequestService
                    .getAllProductModelFromWarehouseByIds(request, TransactionIds);
            List<ProductModel> productModels = responseFromWareHouseProduct != null ? responseFromWareHouseProduct.getResult() : new ArrayList<>();
            //Set sản phẩm có trong transaction importing
            for (ImportingReturnBillTransactionDto i : ExportingTransactionDtos) {
                //lấy tất cả sản phẩm tồn tại trong importingTransaction
                ProductModel product = productModels
                        .stream()
                        .filter(productModel -> productModel.getId().equals(i.getProduct().getId()))
                        .findFirst().orElse(null);
                i.setProduct(product);
            }

            ImportingReturnBillFullDto ImportingFullDto = new ImportingReturnBillFullDto();
            ImportingFullDto.setImportbill(exportingDto);
            ImportingFullDto.setImportingTransactions(ExportingTransactionDtos);
            return ImportingFullDto;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void deleteImportingReturnfullByid(String id) {
        try {
            List<ImportingBillReturnTransactionEntity> importingTransactionEntities = iImportingReturnTransactionRepository.findTransactionbyId(id);
            iImportingReturnTransactionRepository.deleteAll(importingTransactionEntities);
            iImportingReturnBIllRepository.deleteById(id);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public List<ImportingReturnBillFullDto> getAllImportingbReturnill(HttpServletRequest request) throws IOException {

        List<ImportingReturnbillEntity> exportReturnbillEntities = this.iImportingReturnBIllRepository.getAllBill();
        List<ImportingReturnBIllDto> ImportingReturnBillDtos = IImportingReturnBillMapper.INSTANCE.toFromImportingReturnbillsDto(exportReturnbillEntities);

        List<String> ids = ImportingReturnBillDtos.stream().map(ImportingReturnBIllDto::getId).collect(Collectors.toList());

        List<ImportingBillReturnTransactionEntity> exportingBillTransactionEntities = this.iImportingReturnTransactionRepository.getAllDetails(ids);
        List<ImportingReturnBillTransactionDto> exportingBillTransactionDtos = IImportingReturnBIllTransactionMapper.
                INSTANCE.toImportingReturnBillTransactionsDto(exportingBillTransactionEntities);

        //lấy ra hết ids sản phẩm để gọi qua warehouse lấy full thông tin
        List<String> productIds = exportingBillTransactionDtos.stream()
                .map(ImportingReturnBillTransactionDto::getProduct).map(ProductModel::getId).distinct()
                .collect(Collectors.toList());

        ResponseModel<List<ProductModel>> responseFromWareHouse = productIds.size() > 0 ? warehouseRequestService
                .getAllProductModelFromWarehouseByIds(request, productIds) : null;

        List<ProductModel> productModels = responseFromWareHouse != null ? responseFromWareHouse.getResult() : new ArrayList<>();

        // duyệt qua từng hóa đơn đặt hàng
        List<ImportingReturnBillFullDto> exportingBillFullDtos = new ArrayList<>();
        for (ImportingReturnBIllDto e : ImportingReturnBillDtos) {
            ImportingReturnBillFullDto export = new ImportingReturnBillFullDto();
            export.setImportbill(e);
            //lấy hết tất cả chi tiết
            List<ImportingReturnBillTransactionDto> details = exportingBillTransactionDtos
                    .stream().filter(detail -> detail.getBill().getId().equals(e.getId())).collect(Collectors.toList());
            for (ImportingReturnBillTransactionDto detail : details) {
                detail.setBill(null);
                ProductModel productOfDetail = productModels
                        .stream().filter(product -> product.getId().equals(detail.getProduct().getId())).findFirst().orElse(null);
                detail.setProduct(productOfDetail);
            }
            export.setImportingTransactions(details);
            exportingBillTransactionDtos.removeAll(details);
            exportingBillFullDtos.add(export);
        }
        return exportingBillFullDtos;
    }

    @Transactional
    @Override
    public ImportingReturnBillSearchDto getAllImportingReturnBillUseBaseSearch(HttpServletRequest request) throws IOException {
        int a = 0;
        try {
            List<ImportingReturnbillEntity> importingReturn = iImportingReturnBIllRepository.findAll();
            List<ImportingReturnBIllDto> billReturn = IImportingReturnBillMapper.INSTANCE
                    .toFromImportingReturnbillsDto(importingReturn);
            List<String> billId = billReturn.stream().map(ImportingReturnBIllDto::getId).collect(Collectors.toList());

            List<ImportingBillReturnTransactionEntity> exportReturnTransaction =
                    iImportingReturnTransactionRepository.getAllDetails(billId);
            List<ImportingReturnBillTransactionDto> transactions = IImportingReturnBIllTransactionMapper
                    .INSTANCE.toImportingReturnBillTransactionsDto(exportReturnTransaction);
            List<String> customerId = billReturn.stream().map(ImportingReturnBIllDto::getCustomer).
                    map(CustomerModel::getId).collect(Collectors.toList());

            List<String> Ids = billReturn.stream()
                    .map(ImportingReturnBIllDto::getExporting)
                    .map(ExportingBillDto::getId)
                    .collect(Collectors.toList());

            List<ExportbillEntity> exportingEntities = iExportingbillRepository.findAllExportingIds(Ids);
            List<ExportingBillDto> exportingDtos = IExportingbillMapper.INSTANCE
                    .toFromExportingbillDto(exportingEntities);
            for (ImportingReturnBIllDto exporting : billReturn) {
                List<ExportingBillDto> result = exportingDtos.stream()
                        .filter(option ->exporting.getExporting().getId().equals(option.getId()))
                        .collect(Collectors.toList());
                exporting.setExporting(result.size() == 0 ? null : result.get(0));
            }

            ResponseModel<List<CustomerModel>> reponeFromWareHouseCustomer = warehouseRequestService
                    .getCustomerModelFromWarehouseByIds(request, customerId);
            List<CustomerModel> customerModels = reponeFromWareHouseCustomer != null ? reponeFromWareHouseCustomer.getResult() : new ArrayList<>();

            List<String> productIds = transactions.stream()
                    .map(ImportingReturnBillTransactionDto::getProduct).map(ProductModel::getId).distinct()
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> responseFromWareHouse = productIds.size() > 0 ? warehouseRequestService
                    .getAllProductModelFromWarehouseByIds(request, productIds) : null;

            List<ProductModel> productModels =
                    responseFromWareHouse != null ? responseFromWareHouse.getResult() : new ArrayList<>();

            for (ImportingReturnBIllDto e : billReturn) {
                CustomerModel customerModel = customerModels.stream()
                        .filter(customerModel1 -> customerModel1.getId().equals(e.getCustomer().getId()))
                        .findFirst().orElse(null);
                e.setCustomer(customerModel);
            }
            List<ImportingReturnBillFullDto> exportingBillFullDtos = new ArrayList<>();

            for (int i = 0; i < Math.min(transactions.size(), billReturn.size()); i++) {

                ImportingReturnBillTransactionDto transaction = transactions.get(i);
                ImportingReturnBIllDto billDto = billReturn.get(i);
                ImportingReturnBillFullDto exportingBillFullDto = new ImportingReturnBillFullDto();
                exportingBillFullDto.setImportbill(billDto);
                ProductModel productOfDetail = productModels
                        .stream().filter(product -> product.getId().equals(
                                transaction.getProduct().getId())).findFirst().orElse(null);
                transaction.setProduct(productOfDetail);
                List<ImportingReturnBillTransactionDto> transactionList = new ArrayList<>();
                transactionList.add(transaction);
                exportingBillFullDto.setImportingTransactions(transactionList);
                exportingBillFullDtos.add(exportingBillFullDto);
            }


            ImportingReturnBillSearchDto result = new ImportingReturnBillSearchDto();
            result.setResult(exportingBillFullDtos);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateImportingReturn(ImportingReturnBillFullDto importingReturnBillFullDto) {
        try {
            String importId = importingReturnBillFullDto.getImportbill().getExporting().getId();
            List<ExportingBillTransactionEntity> listTransanOld = iExportingTransactionRepository.findTransactionbyId(importId);
            List<ExportingBillTransactionDto> listTransactionDto = IExportingbillTransactionMapper
                    .INSTANCE.toExportingBillTransactionDtoList(listTransanOld);

            // Kiểm tra xem danh sách giao dịch nhập có rỗng hay không
            if (!listTransactionDto.isEmpty()) {
                for (ImportingReturnBillTransactionDto transaction : importingReturnBillFullDto
                        .getImportingTransactions()) {
                    String id = transaction.getProduct().getId();
                    int quantity = transaction.getQuantity();

                    boolean isMatch = false;
                    // Duyệt qua từng giao dịch nhập để kiểm tra
                    for (ExportingBillTransactionDto ExportingTransaction : listTransactionDto) {
                        if (ExportingTransaction.getProduct().equals(id) && quantity <= ExportingTransaction.getQuantity()) {
                            isMatch = true;
                            break;
                        }
                    }

                    // Nếu không tìm thấy giao dịch nhập phù hợp, bỏ qua và tiếp tục với giao dịch kế tiếp
                    if (!isMatch) {
                        continue;
                    }
                    String exportingId = importingReturnBillFullDto.getImportbill().getId();

                    List<ImportingBillReturnTransactionEntity> exportingTransactionEntities = iImportingReturnTransactionRepository
                            .findTransactionbyId(exportingId);
                    iImportingReturnTransactionRepository.deleteAll(exportingTransactionEntities);

                    // Cập nhật thông tin của importing
                    ImportingReturnbillEntity exportingEntity = iImportingReturnBIllRepository.findById(exportingId)
                            .orElseThrow(() -> new RuntimeException("Payment with id " + exportingId + " not found."));

                    // Cập nhật thông tin của importing từ importingDto
                    ImportingReturnBIllDto updatedExportingDto = importingReturnBillFullDto.getImportbill();
                    exportingEntity.setCode(updatedExportingDto.getCode());
                    exportingEntity.setStatus(updatedExportingDto.getStatus());
                    exportingEntity.setTotal(updatedExportingDto.getTotal());
                    exportingEntity.setCustomerId(updatedExportingDto.getCustomer().getId());
                    exportingEntity.setAgencyId(updatedExportingDto.getAgency().getId());
                    exportingEntity.setDateExport(LocalDateTime.now());

                    // Lưu lại thông tin importing đã cập nhật
                    iImportingReturnBIllRepository.save(exportingEntity);

                    List<ImportingBillReturnTransactionEntity> exportingTransactionUpdate = IImportingReturnBIllTransactionMapper
                            .INSTANCE.toFromImportingReturnbillTransactionsDto(importingReturnBillFullDto.getImportingTransactions());

                    // Cập nhật lại exportingId cho các ImportingTransactionEntity
                    for (ImportingBillReturnTransactionEntity transactionEntity : exportingTransactionUpdate) {
                        transactionEntity.setBillID(exportingId);
                    }

                    // Lưu lại thông tin các ImportingTransactionEntity đã cập nhật
                    iImportingReturnTransactionRepository.saveAll(exportingTransactionUpdate);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
