package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingBillTransactionDto;
import com.example.ctcommondal.entity.ExportingBillTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IExportingbillTransactionMapper {
    IExportingbillTransactionMapper INSTANCE = Mappers.getMapper(IExportingbillTransactionMapper.class);

    @Mapping(target = "product.id", source = "productID")
    @Mapping(target = "bill.id", source = "billID")
    @Mapping(target = "color.id", source = "color")
    @Mapping(target = "size.id", source = "size")
    ExportingBillTransactionDto toExportingBillTransactionDto(ExportingBillTransactionEntity transactionEntity);
    List<ExportingBillTransactionDto> toExportingBillTransactionDtoList(List<ExportingBillTransactionEntity> transactionEntities);

    @Mapping(target = "productID", source = "product.id")
    @Mapping(target = "billID", source = "bill.id")
    @Mapping(target = "color", source = "color.id")
    @Mapping(target = "size", source = "size.id")
    ExportingBillTransactionEntity toFromExportingbillTransactionDto(ExportingBillTransactionDto exportingTransactionDto);
    List<ExportingBillTransactionEntity> toListFromExportingbillTransactionDto(List<ExportingBillTransactionDto> exportingTransactionDto);
}