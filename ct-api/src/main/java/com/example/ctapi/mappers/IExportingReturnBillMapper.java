package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingReturnBillDto;
import com.example.ctapi.dtos.response.ImportingDto;
import com.example.ctcommondal.entity.ExportingReturnBillEntity;
import com.example.ctcommondal.entity.ImportingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IExportingReturnBillMapper {
    IExportingReturnBillMapper INSTANCE = Mappers.getMapper(IExportingReturnBillMapper.class);


    @Mapping(target = "agencyId", source = "agency.id")
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "importingId", source = "importing.id")
    ExportingReturnBillEntity toFromImportingReturnbillDto(ExportingReturnBillDto exportingReturnBillDto);

    List<ExportingReturnBillEntity> toFromListImportingReturnbillDto(List<ExportingReturnBillDto> exportingReturnBillDto);

    @Mapping(target = "agency.id",source = "agencyId")
    @Mapping(target = "supplier.id",source = "supplierId")
    @Mapping(target = "status",source = "status")
    @Mapping(target = "importing.id", source = "importingId")
    ExportingReturnBillDto toFromExportingReturnEntity(ExportingReturnBillEntity exportingReturnBillEntity);
    List<ExportingReturnBillDto> toFromListExportingReturnbillEntity(List<ExportingReturnBillEntity> exportingReturnBillEntities);

}
