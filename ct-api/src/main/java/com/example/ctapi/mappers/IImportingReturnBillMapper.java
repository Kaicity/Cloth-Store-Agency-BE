package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingBillDto;
import com.example.ctapi.dtos.response.ImportingReturnBIllDto;
import com.example.ctcommondal.entity.ExportbillEntity;
import com.example.ctcommondal.entity.ImportingReturnbillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IImportingReturnBillMapper {
    IImportingReturnBillMapper INSTANCE = Mappers.getMapper(IImportingReturnBillMapper.class);
    @Mapping(target = "status", source = "status")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "agencyId", source = "agency.id")
    @Mapping(target = "dateExport", source = "dateExport")
    @Mapping(target = "eid",source = "customerNotLogin.eid")
    @Mapping(target = "exportingId",source = "exporting.id")
    ImportingReturnbillEntity toFromExportingReturnbillEntity(ImportingReturnBIllDto exportingBillDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "agency.id", source = "agencyId")
    @Mapping(target = "exporting.id",source = "exportingId")
    ImportingReturnBIllDto toFromImportingReturnbillDto(ImportingReturnbillEntity exportingBillDto);

    List<ImportingReturnBIllDto> toFromImportingReturnbillsDto(List<ImportingReturnbillEntity> exportingBillDto);
}
