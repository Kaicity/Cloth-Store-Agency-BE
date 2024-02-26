package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.ImportingDto;
import com.example.ctapi.dtos.response.ImportingFullDto;
import com.example.ctapi.dtos.response.ImportingSearchDto;
import com.example.ctapi.dtos.response.ImportingTransactionDto;
import com.example.ctapi.mappers.IImportingMapper;
import com.example.ctapi.mappers.IImportingTransactionMapper;
import com.example.ctapi.services.IImportingService;
import com.example.ctcommon.enums.ImportingStatus;
import com.example.ctcommondal.entity.ImportingEntity;
import com.example.ctcommondal.entity.ImportingTransactionEntity;
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
public class IImportingServiceImpl implements IImportingService {

    private final Logger logger = LoggerFactory.getLogger(IImportingServiceImpl.class);
    private final IImportingRepository iImportingRepository;
    private final IImportingTransactionRepository iImportingTransactionRepository;
    private final IWarehouseRequestService warehouseRequestService;


    @Transactional
    @Override
    public void createImporting(ImportingFullDto importingFull) {
        try {
            //set status Importing
            importingFull.getImporting().setStatus(ImportingStatus.UNCOMPLETE);
            ImportingEntity importingEntity = IImportingMapper.INSTANCE.toFromImportingDto(importingFull.getImporting());
            iImportingRepository.save(importingEntity);

            //duyệt qua vòng lặp
            for (ImportingTransactionDto detail : importingFull.getImportingTransactions()) {
                detail.setImporting(importingFull.getImporting());
            }
            List<ImportingTransactionEntity> importingTransactionEntities =
                    IImportingTransactionMapper.INSTANCE.toFromImportingTransactionDtoList(importingFull.getImportingTransactions());
            iImportingTransactionRepository.saveAll(importingTransactionEntities);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateImporting(ImportingFullDto importingFull) {
        try {
            String importingId = importingFull.getImporting().getId();

            // Xóa các PaymentTransaction trước
            List<ImportingTransactionEntity> importingTransactionEntities = iImportingTransactionRepository.findImportingId(importingId);
            iImportingTransactionRepository.deleteAll(importingTransactionEntities);

            // Cập nhật thông tin của importing
            ImportingEntity importingEntity = iImportingRepository.findById(importingId)
                    .orElseThrow(() -> new RuntimeException("Payment with id " + importingId + " not found."));

            // Cập nhật thông tin của importing từ importingDto
            ImportingDto updatedImportingDto = importingFull.getImporting();
            importingEntity.setCode(updatedImportingDto.getCode());
            importingEntity.setStatus(updatedImportingDto.getStatus());
            importingEntity.setTotal(updatedImportingDto.getTotal());
            importingEntity.setSupplierId(updatedImportingDto.getSupplier().getId());
            importingEntity.setAgencyId(updatedImportingDto.getAgency().getId());
            importingEntity.setDateUpdated(LocalDateTime.now());
            importingEntity.setDateCreated(updatedImportingDto.getDateCreated());

            // Lưu lại thông tin importing đã cập nhật
            iImportingRepository.save(importingEntity);

            List<ImportingTransactionEntity> importingTransactionUpdate = IImportingTransactionMapper
                    .INSTANCE.toFromImportingTransactionDtoList(importingFull.getImportingTransactions());

            // Cập nhật lại importingId cho các ImportingTransactionEntity
            for (ImportingTransactionEntity transactionEntity : importingTransactionUpdate) {
                transactionEntity.setImportingId(importingId);
            }

            // Lưu lại thông tin các ImportingTransactionEntity đã cập nhật
            iImportingTransactionRepository.saveAll(importingTransactionUpdate);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void deleteImportingFullByid(String id) {
        try {
            List<ImportingTransactionEntity> importingTransactionEntities = iImportingTransactionRepository.findImportingId(id);
            iImportingTransactionRepository.deleteAll(importingTransactionEntities);
            iImportingRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public ImportingSearchDto getAllImportingFull(HttpServletRequest request) throws IOException {
        try {
            int a = 0;
            List<ImportingEntity> importingEntities = this.iImportingRepository.findAll();
            List<ImportingDto> importingDtos = IImportingMapper.INSTANCE.toFromImportingEntityList(importingEntities);

            //Trả về danh sách id supplier theo importing
            List<String> importIds = importingDtos.stream()
                    .map(ImportingDto::getSupplier)
                    .map(SupplierModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<SupplierModel>> responseFromWareHouseSupplier = !importingDtos.isEmpty() ? warehouseRequestService
                    .getSupplierFromWarehouseByIds(request, importIds) : null;
            List<SupplierModel> supplierModels = responseFromWareHouseSupplier != null ? responseFromWareHouseSupplier.getResult() : new ArrayList<>();
            for (ImportingDto i : importingDtos) {
                //lấy tất cả nhà cung cấp trong importing
                SupplierModel supplier = supplierModels
                        .stream()
                        .filter(supplierModel -> supplierModel.getId().equals(i.getSupplier().getId()))
                        .findFirst().orElse(null);
                i.setSupplier(supplier);
            }

            //-----------------
            //Danh sách ids tham chiếu đến importingTransaction
            List<String> ids = importingDtos.stream().map(ImportingDto::getId).collect(Collectors.toList());

            List<ImportingTransactionEntity> importingTransactionEntities = this.iImportingTransactionRepository.getAllDetails(ids);
            List<ImportingTransactionDto> importingTransactionDtos = IImportingTransactionMapper
                    .INSTANCE.toFromImportingTransactionEntityList(importingTransactionEntities);

            //Trả về danh sách các product có trong importingTransaction
            List<String> importingTransactionIds = importingTransactionDtos.stream()
                    .map(ImportingTransactionDto::getProduct)
                    .map(ProductModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> reponseFromWareHouseProduct = !importingTransactionDtos.isEmpty() ?
                    warehouseRequestService.getAllProductModelFromWarehouseByIds(request, importingTransactionIds) : null;
            List<ProductModel> productModels = reponseFromWareHouseProduct != null ? reponseFromWareHouseProduct.getResult() : new ArrayList<>();
            for (ImportingTransactionDto i : importingTransactionDtos) {
                //lấy tất cả sản phẩm tồn tại trong importingTransaction
                ProductModel product = productModels
                        .stream()
                        .filter(productModel -> productModel.getId().equals(i.getProduct().getId()))
                        .findFirst().orElse(null);
                i.setProduct(product);
            }

            // Duyệt qua từng đơn đặt hàng
            List<ImportingFullDto> importingFullDtos = new ArrayList<>();
            for (ImportingDto i : importingDtos) {
                ImportingFullDto importingFullDto = new ImportingFullDto();
                importingFullDto.setImporting(i);
                //lấy hết tất cả chi tiết
                List<ImportingTransactionDto> details = importingTransactionDtos
                        .stream().filter(detail -> detail.getImporting().getId().equals(i.getId()))
                        .collect(Collectors.toList());

                importingFullDto.setImportingTransactions(details);
                importingFullDtos.add(importingFullDto);
            }

            ImportingSearchDto result = new ImportingSearchDto();
            result.setResult(importingFullDtos);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public ImportingFullDto getImportingById(HttpServletRequest request, String id) throws IOException {
        try {
            ImportingEntity importingEntity = iImportingRepository.findImportingById(id);
            ImportingDto importingDto = IImportingMapper.INSTANCE.toFromImportingEntity(importingEntity);

            //Trả về danh sách id supplier theo importing
            List<String> importIds = new ArrayList<>();
            importIds.add(importingDto.getSupplier().getId());

            ResponseModel<List<SupplierModel>> responseFromWareHouseSupplier = warehouseRequestService
                    .getSupplierFromWarehouseByIds(request, importIds);
            List<SupplierModel> supplierModels = responseFromWareHouseSupplier != null ? responseFromWareHouseSupplier.getResult() : new ArrayList<>();

            if (supplierModels.size() == 0) importingDto.setSupplier(null);
            else importingDto.setSupplier(supplierModels.get(0));


            List<ImportingTransactionEntity> importingTransactionEntities = iImportingTransactionRepository.findImportingId(id);
            List<ImportingTransactionDto> importingTransactionDtos = IImportingTransactionMapper
                    .INSTANCE.toFromImportingTransactionEntityList(importingTransactionEntities);

            //Trả về danh sách các product có trong importingTransaction
            List<String> importingTransactionIds = importingTransactionDtos.stream()
                    .map(ImportingTransactionDto::getProduct)
                    .map(ProductModel::getId)
                    .collect(Collectors.toList());

            ResponseModel<List<ProductModel>> responseFromWareHouseProduct = warehouseRequestService
                    .getAllProductModelFromWarehouseByIds(request, importingTransactionIds);
            List<ProductModel> productModels = responseFromWareHouseProduct != null ? responseFromWareHouseProduct.getResult() : new ArrayList<>();
            //Set sản phẩm có trong transaction importing
            for (ImportingTransactionDto i : importingTransactionDtos) {
                //lấy tất cả sản phẩm tồn tại trong importingTransaction
                ProductModel product = productModels
                        .stream()
                        .filter(productModel -> productModel.getId().equals(i.getProduct().getId()))
                        .findFirst().orElse(null);
                i.setProduct(product);
            }

            ImportingFullDto importingFullDto = new ImportingFullDto();
            importingFullDto.setImporting(importingDto);
            importingFullDto.setImportingTransactions(importingTransactionDtos);
            return importingFullDto;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
