package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingBillTransactionDto;
import com.example.ctapi.dtos.response.ImportingReturnBillTransactionDto;
import com.example.ctcommondal.entity.ExportingBillTransactionEntity;
import com.example.ctcommondal.entity.ImportingBillReturnTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IImportingReturnBIllTransactionMapper {
    IImportingReturnBIllTransactionMapper INSTANCE = Mappers.getMapper(IImportingReturnBIllTransactionMapper.class);
    @Mapping(target = "product.id", source = "productID")
    @Mapping(target = "bill.id", source = "billID")
    @Mapping(target = "color.id", source = "color")
    @Mapping(target = "size.id", source = "size")
    ImportingReturnBillTransactionDto toImportingReturnBillTransactionDto(ImportingBillReturnTransactionEntity transactionEntity);
    List<ImportingReturnBillTransactionDto> toImportingReturnBillTransactionsDto(List<ImportingBillReturnTransactionEntity> transactionEntities);

    @Mapping(target = "productID", source = "product.id")
    @Mapping(target = "billID", source = "bill.id")
    @Mapping(target = "color", source = "color.id")
    @Mapping(target = "size", source = "size.id")
    ImportingBillReturnTransactionEntity toFromImportingReturnbillTransactionDto(ImportingReturnBillTransactionDto exportingTransactionDto);
    List<ImportingBillReturnTransactionEntity> toFromImportingReturnbillTransactionsDto(List<ImportingReturnBillTransactionDto> exportingTransactionDto);

}
