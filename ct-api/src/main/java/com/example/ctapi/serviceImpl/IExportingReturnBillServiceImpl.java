package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.*;
import com.example.ctapi.mappers.IExportingReturnBillMapper;
import com.example.ctapi.mappers.IExportingReturnTransactionMapper;
import com.example.ctapi.mappers.IImportingMapper;
import com.example.ctapi.mappers.IImportingTransactionMapper;
import com.example.ctapi.services.IExportingReturnBillService;
import com.example.ctcommon.enums.ImportingStatus;
import com.example.ctcommondal.entity.ExportingReturnBillEntity;
import com.example.ctcommondal.entity.ExportingReturnTransactionEntity;
import com.example.ctcommondal.entity.ImportingEntity;
import com.example.ctcommondal.entity.ImportingTransactionEntity;
import com.example.ctcommondal.repository.IExportingReturnBillRepository;
import com.example.ctcommondal.repository.IExportingReturnTransactionRepository;
import com.example.ctcommondal.repository.IImportingRepository;
import com.example.ctcommondal.repository.IImportingTransactionRepository;
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
import java.time.LocalDateTime;
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
    private final IImportingRepository iImportingRepository;
    private final IImportingTransactionRepository iImportingTransactionRepository;

    @Transactional
    @Override
    public ExportingReturnBillSearchDto getAllExportingReturnFull(HttpServletRequest request) throws IOException {

        try {
            int a = 0;
            List<ExportingReturnBillEntity> exportingReturnEntities = this.exportingReturnBillRepository.findAll();
            List<ExportingReturnBillDto> ExportingDtos = IExportingReturnBillMapper.
                    INSTANCE.toFromListExportingReturnbillEntity(exportingReturnEntities);
            //Trả về danh sách id importing theo exportingReturn
            List<String> ExportIds = ExportingDtos.stream()
                    .map(ExportingReturnBillDto::getSupplier)
                    .map(SupplierModel::getId)
                    .collect(Collectors.toList());
            List<String> Ids = ExportingDtos.stream()
                    .map(ExportingReturnBillDto::getImporting)
                    .map(ImportingDto::getId)
                    .collect(Collectors.toList());
            List<ImportingEntity> importingEntities = iImportingRepository.findAllImportingIds(Ids);
            List<ImportingDto> ImportingDtos = IImportingMapper.INSTANCE.toFromImportingEntityList(importingEntities);

            for (ExportingReturnBillDto importing : ExportingDtos) {
                List<ImportingDto> result = ImportingDtos.stream()
                        .filter(option ->importing.getImporting().getId().equals(option.getId()))
                        .collect(Collectors.toList());
                importing.setImporting(result.size() == 0 ? null : result.get(0));
            }

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

    @Override
    @Transactional
    public void deleteExortingReturnFullByid(String id) {
        try {
            List<ExportingReturnTransactionEntity> ExportingTransactionEntities = exportingReturnTransactionRepository.findExportingReturnId(id);
            exportingReturnTransactionRepository.deleteAll(ExportingTransactionEntities);
            exportingReturnBillRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }


    @Transactional
    @Override
    public ExportingReturnBillFullDto getExportingReturnById(HttpServletRequest request, String id) throws IOException {
        try {
            ExportingReturnBillEntity ExportingReturnEntity = exportingReturnBillRepository.findExportingReturnById(id);
            ExportingReturnBillDto ExportingReturnDto = IExportingReturnBillMapper.INSTANCE.toFromExportingReturnEntity(ExportingReturnEntity);

            //Trả về danh sách id supplier theo importing
            List<String> importIds = new ArrayList<>();
            importIds.add(ExportingReturnDto.getSupplier().getId());

            ResponseModel<List<SupplierModel>> responseFromWareHouseSupplier = warehouseRequestService
                    .getSupplierFromWarehouseByIds(request, importIds);
            List<SupplierModel> supplierModels = responseFromWareHouseSupplier != null ? responseFromWareHouseSupplier.getResult() : new ArrayList<>();

            if (supplierModels.size() == 0) ExportingReturnDto.setSupplier(null);
            else ExportingReturnDto.setSupplier(supplierModels.get(0));


            List<ExportingReturnTransactionEntity> ExportingReturnTransactionEntities =
                    exportingReturnTransactionRepository.findExportingReturnListId(id);
            List<ExportingReturnTransactionDto> ExportingReturnTransactionDtos = IExportingReturnTransactionMapper
                    .INSTANCE.toFromImportingReturnTransactionEntityList(ExportingReturnTransactionEntities);

            //Trả về danh sách các product có trong importingTransaction
            List<String> importingTransactionIds = ExportingReturnTransactionDtos.stream()
                    .map(ExportingReturnTransactionDto::getProduct)
                    .map(ProductModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> responseFromWareHouseProduct = warehouseRequestService
                    .getAllProductModelFromWarehouseByIds(request, importingTransactionIds);
            List<ProductModel> productModels = responseFromWareHouseProduct != null ? responseFromWareHouseProduct.getResult() : new ArrayList<>();
            //Set sản phẩm có trong transaction importing
            for (ExportingReturnTransactionDto i : ExportingReturnTransactionDtos) {
                //lấy tất cả sản phẩm tồn tại trong importingTransaction
                ProductModel product = productModels
                        .stream()
                        .filter(productModel -> productModel.getId().equals(i.getProduct().getId()))
                        .findFirst().orElse(null);
                i.setProduct(product);
            }

            ExportingReturnBillFullDto returnFUllDto = new ExportingReturnBillFullDto();
            returnFUllDto.setExportingReturnBill(ExportingReturnDto);
            returnFUllDto.setExportingReturnTransactionList(ExportingReturnTransactionDtos);
            return returnFUllDto;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateExportingReturn(ExportingReturnBillFullDto exportingReturnBillFullDto) {
        try {
            String importId = exportingReturnBillFullDto.getExportingReturnBill().getImporting().getId();
            List<ImportingTransactionEntity> listTransanOld = iImportingTransactionRepository.findListImportingTransactionId(importId);
            List<ImportingTransactionDto> listTransactionDto = IImportingTransactionMapper.INSTANCE.toFromImportingTransactionEntityList(listTransanOld);

            // Kiểm tra xem danh sách giao dịch nhập có rỗng hay không
            if (!listTransactionDto.isEmpty()) {
                for (ExportingReturnTransactionDto transaction : exportingReturnBillFullDto
                        .getExportingReturnTransactionList()) {
                    String id = transaction.getProduct().getId();
                    int quantity = transaction.getQuantity();

                    boolean isMatch = false; // Biến để kiểm tra xem có giao dịch nhập phù hợp hay không

                    // Duyệt qua từng giao dịch nhập để kiểm tra
                    for (ImportingTransactionDto importingTransaction : listTransactionDto) {
                        if (importingTransaction.getProduct().equals(id) && quantity <= importingTransaction.getQuantity()) {
                            isMatch = true;
                            break;
                        }
                    }

                    // Nếu không tìm thấy giao dịch nhập phù hợp, bỏ qua và tiếp tục với giao dịch kế tiếp
                    if (!isMatch) {
                        continue;
                    }
                    String ExportingReturnId = exportingReturnBillFullDto.getExportingReturnBill().getId();
                    List<ExportingReturnTransactionEntity> exportingReturnTransactionEntities = exportingReturnTransactionRepository
                            .findExportingReturnListId(ExportingReturnId);
                    exportingReturnTransactionRepository.deleteAll(exportingReturnTransactionEntities);

                    ExportingReturnBillEntity ExportingEntity = exportingReturnBillRepository.findById(ExportingReturnId)
                            .orElseThrow(() -> new RuntimeException("Payment with id " + ExportingReturnId + " not found."));

                    ExportingReturnBillDto updatedImportingDto = exportingReturnBillFullDto.getExportingReturnBill();
                    ExportingEntity.setCode(updatedImportingDto.getCode());
                    ExportingEntity.setStatus(updatedImportingDto.getStatus());
                    ExportingEntity.setTotal(updatedImportingDto.getTotal());
                    ExportingEntity.setSupplierId(updatedImportingDto.getSupplier().getId());
                    ExportingEntity.setAgencyId(updatedImportingDto.getAgency().getId());
                    ExportingEntity.setDateUpdated(LocalDateTime.now());
                    ExportingEntity.setDateExport(updatedImportingDto.getDateExport());

                    // Lưu lại thông tin importing đã cập nhật
                    exportingReturnBillRepository.save(ExportingEntity);

                    List<ExportingReturnTransactionEntity> ExportingReturnTransactionUpdate = IExportingReturnTransactionMapper
                            .INSTANCE.toFromImportingReturnTransactionDtoList(exportingReturnBillFullDto.getExportingReturnTransactionList());

                    // Cập nhật lại ExportingReturnId cho các ImportingTransactionEntity
                    for (ExportingReturnTransactionEntity transactionEntity : ExportingReturnTransactionUpdate) {
                        transactionEntity.setExportReturnId(ExportingReturnId);
                    }

                    // Lưu lại thông tin các ImportingTransactionEntity đã cập nhật
                    exportingReturnTransactionRepository.saveAll(ExportingReturnTransactionUpdate);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

    }

    @Transactional
    @Override
    public void createExportingReturn(ExportingReturnBillFullDto exportingReturnBillFullDto) {
        try {
            String importId = exportingReturnBillFullDto.getExportingReturnBill().getImporting().getId();
            List<ImportingTransactionEntity> listTransanOld = iImportingTransactionRepository.findListImportingTransactionId(importId);
            List<ImportingTransactionDto> listTransactionDto = IImportingTransactionMapper.INSTANCE.toFromImportingTransactionEntityList(listTransanOld);

            // Kiểm tra xem danh sách giao dịch nhập có rỗng hay không
            if (!listTransactionDto.isEmpty()) {
                for (ExportingReturnTransactionDto transaction : exportingReturnBillFullDto
                        .getExportingReturnTransactionList()) {
                    String id = transaction.getProduct().getId();
                    int quantity = transaction.getQuantity();

                    boolean isMatch = false; // Biến để kiểm tra xem có giao dịch nhập phù hợp hay không

                    // Duyệt qua từng giao dịch nhập để kiểm tra
                    for (ImportingTransactionDto importingTransaction : listTransactionDto) {
                        if (importingTransaction.getProduct().equals(id) && quantity <= importingTransaction.getQuantity()) {
                            isMatch = true;
                            break;
                        }
                    }

                    // Nếu không tìm thấy giao dịch nhập phù hợp, bỏ qua và tiếp tục với giao dịch kế tiếp
                    if (!isMatch) {
                        continue;
                    }

                    // Nếu có giao dịch nhập phù hợp, thực hiện cập nhật trạng thái và lưu dữ liệu
                    exportingReturnBillFullDto.getExportingReturnBill().setStatus(ImportingStatus.UNCOMPLETE);
                    ExportingReturnBillEntity exportingEntity = IExportingReturnBillMapper.INSTANCE.toFromImportingReturnbillDto(exportingReturnBillFullDto.getExportingReturnBill());
                    exportingReturnBillRepository.save(exportingEntity);

                    exportingReturnBillFullDto.getExportingReturnTransactionList().forEach(detail ->
                            detail.setExportingReturnBill(exportingReturnBillFullDto.getExportingReturnBill()));

                    List<ExportingReturnTransactionEntity> exportingReturnTransactionEntities =
                            IExportingReturnTransactionMapper.INSTANCE.toFromImportingReturnTransactionDtoList(exportingReturnBillFullDto.getExportingReturnTransactionList());

                    exportingReturnTransactionRepository.saveAll(exportingReturnTransactionEntities);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }


}
