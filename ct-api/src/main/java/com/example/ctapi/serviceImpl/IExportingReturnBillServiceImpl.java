package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.ExportingReturnBillDto;
import com.example.ctapi.dtos.response.ExportingReturnBillFullDto;
import com.example.ctapi.dtos.response.ExportingReturnBillSearchDto;
import com.example.ctapi.dtos.response.ExportingReturnTransactionDto;
import com.example.ctapi.mappers.IExportingReturnBillMapper;
import com.example.ctapi.mappers.IExportingReturnTransactionMapper;
import com.example.ctapi.services.IExportingReturnBillService;
import com.example.ctcommondal.entity.ExportingReturnBillEntity;
import com.example.ctcommondal.entity.ExportingReturnTransactionEntity;
import com.example.ctcommondal.repository.IExportingReturnBillRepository;
import com.example.ctcommondal.repository.IExportingReturnTransactionRepository;
import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.ResponseModel;
import com.example.ctcoremodel.SupplierModel;
import com.example.ctcoreservice.services.IWarehouseRequestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IExportingReturnBillServiceImpl implements IExportingReturnBillService {

    private final Logger logger = LoggerFactory.getLogger(IExportingReturnBillServiceImpl.class);
    private final IExportingReturnBillRepository exportingReturnBillRepository;
    private final IWarehouseRequestService warehouseRequestService;
    private final IExportingReturnTransactionRepository exportingReturnTransactionRepository;

    @Transactional
    @Override
    public ExportingReturnBillSearchDto getAllExportingReturnFull(HttpServletRequest request) throws IOException {

        try {
            int a = 0;
            List<ExportingReturnBillEntity> exportingReturnEntities = this.exportingReturnBillRepository.findAll();
            List<ExportingReturnBillDto> ExportingDtos = IExportingReturnBillMapper.
                    INSTANCE.toFromListExportingReturnbillEntity(exportingReturnEntities);

            //Trả về danh sách id supplier theo importing
            List<String> ExportIds = ExportingDtos.stream()
                    .map(ExportingReturnBillDto::getSupplier)
                    .map(SupplierModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<SupplierModel>> responseFromWareHouseSupplier = !ExportingDtos.isEmpty() ? warehouseRequestService
                    .getSupplierFromWarehouseByIds(request, ExportIds) : null;
            List<SupplierModel> supplierModels = responseFromWareHouseSupplier != null ? responseFromWareHouseSupplier.getResult() : new ArrayList<>();
            for (ExportingReturnBillDto i : ExportingDtos) {
                //lấy tất cả nhà cung cấp trong importing
                SupplierModel supplier = supplierModels
                        .stream()
                        .filter(supplierModel -> supplierModel.getId().equals(i.getSupplier().getId()))
                        .findFirst().orElse(null);
                i.setSupplier(supplier);
            }

            //-----------------
            //Danh sách ids tham chiếu đến importingTransaction
            List<String> ids = ExportingDtos.stream().map(ExportingReturnBillDto::getId).collect(Collectors.toList());

            List<ExportingReturnTransactionEntity> EXportingReturnTransactionEntities =
                    this.exportingReturnTransactionRepository.getAllReturnDetails(ids);
            List<ExportingReturnTransactionDto> ExportingTransactionDtos = IExportingReturnTransactionMapper
                    .INSTANCE.toFromImportingReturnTransactionEntityList(EXportingReturnTransactionEntities);

            //Trả về danh sách các product có trong importingTransaction
            List<String> EXportingTransactionIds = ExportingTransactionDtos.stream()
                    .map(ExportingReturnTransactionDto::getProduct)
                    .map(ProductModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> reponseFromWareHouseProduct = !ExportingTransactionDtos.isEmpty() ?
                    warehouseRequestService.getAllProductModelFromWarehouseByIds(request, EXportingTransactionIds) : null;
            List<ProductModel> productModels = reponseFromWareHouseProduct != null ? reponseFromWareHouseProduct.getResult() : new ArrayList<>();
            for (ExportingReturnTransactionDto i : ExportingTransactionDtos) {
                //lấy tất cả sản phẩm tồn tại trong importingTransaction
                ProductModel product = productModels
                        .stream()
                        .filter(productModel -> productModel.getId().equals(i.getProduct().getId()))
                        .findFirst().orElse(null);
                i.setProduct(product);
            }

            // Duyệt qua từng đơn đặt hàng
            List<ExportingReturnBillFullDto> ExportingFullDtos = new ArrayList<>();
            for (ExportingReturnBillDto i : ExportingDtos) {
                ExportingReturnBillFullDto ExportingFullDto = new ExportingReturnBillFullDto();
                ExportingFullDto.setExportingReturnBill(i);
                //lấy hết tất cả chi tiết
                List<ExportingReturnTransactionDto> details = ExportingTransactionDtos
                        .stream().filter(detail -> detail.getExportingReturnBill().getId().equals(i.getId()))
                        .collect(Collectors.toList());

                ExportingFullDto.setExportingReturnTransactionList(details);
                ExportingFullDtos.add(ExportingFullDto);
            }

            ExportingReturnBillSearchDto result = new ExportingReturnBillSearchDto();
            result.setResult(ExportingFullDtos);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
