package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ImportingDto;
import com.example.ctcommondal.entity.ImportingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IImportingMapper {
    IImportingMapper INSTANCE = Mappers.getMapper(IImportingMapper.class);

    @Mapping(target = "agencyId", source = "agency.id")
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "status", source = "status")
    ImportingEntity toFromImportingDto(ImportingDto importingDto);
    List<ImportingEntity> toFromImportingsDto(List<ImportingDto> importingDto);

    @Mapping(target = "agency.id",source = "agencyId")
    @Mapping(target = "supplier.id",source = "supplierId")
    @Mapping(target = "status",source = "status")
    ImportingDto toFromImportingEntity(ImportingEntity importingEntity);

    List<ImportingDto> toFromImportingEntityList(List<ImportingEntity> importingEntities);
}
