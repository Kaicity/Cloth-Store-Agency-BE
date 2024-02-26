package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingReturnTransactionDto;
import com.example.ctcommondal.entity.ExportingReturnTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface IExportingReturnTransactionMapper {
    IExportingReturnTransactionMapper INSTANCE = Mappers.getMapper(IExportingReturnTransactionMapper.class);
    @Mapping(target = "exportReturnId",source = "exportingReturnBill.id")
    @Mapping(target = "productId",source = "product.id")
    ExportingReturnTransactionEntity toFromImportingReturnTransactionDtoList(ExportingReturnTransactionDto exportingReturnTransactionDto);

    List<ExportingReturnTransactionEntity> toFromImportingReturnTransactionDtoList(List<ExportingReturnTransactionDto> listexportingReturnTransactionDto);
    @Mapping(target = "exportingReturnBill.id",source = "exportReturnId")
    @Mapping(target = "product.id",source = "productId")
    ExportingReturnTransactionDto toFromImportingReturnTransactionEntity(ExportingReturnTransactionEntity exportingReturnTransactionEntity);
    List<ExportingReturnTransactionDto> toFromImportingReturnTransactionEntityList(List<ExportingReturnTransactionEntity> exportingReturnTransactionEntities);
}
