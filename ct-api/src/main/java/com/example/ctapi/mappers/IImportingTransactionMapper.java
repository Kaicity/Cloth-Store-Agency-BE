package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ImportingTransactionDto;
import com.example.ctcommondal.entity.ImportingTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IImportingTransactionMapper {
    IImportingTransactionMapper INSTANCE = Mappers.getMapper(IImportingTransactionMapper.class);
    @Mapping(target = "importingId",source = "importing.id")
    @Mapping(target = "productId",source = "product.id")
    ImportingTransactionEntity toFromImportingTransactionDtoList(ImportingTransactionDto importingTransactionDto);

    List<ImportingTransactionEntity> toFromImportingTransactionDtoList(List<ImportingTransactionDto> importingTransactionDtos);
    @Mapping(target = "importing.id",source = "importingId")
    @Mapping(target = "product.id",source = "productId")
    ImportingTransactionDto toFromImportingTransactionEntity(ImportingTransactionEntity importingTransactionEntity);
    List<ImportingTransactionDto> toFromImportingTransactionEntityList(List<ImportingTransactionEntity> importingTransactionEntities);
}
