package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingBillDto;
import com.example.ctcommondal.entity.ExportbillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IExportingbillMapper {
    IExportingbillMapper INSTANCE = Mappers.getMapper(IExportingbillMapper.class);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "agencyId", source = "agency.id")
    @Mapping(target = "dateExport", source = "dateExport")
    @Mapping(target = "eid",source = "customerNotLogin.eid")
    ExportbillEntity toFromExportingbillEntity(ExportingBillDto exportingBillDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "agency.id", source = "agencyId")
    ExportingBillDto toFromExportingbillDto(ExportbillEntity exportingBillDto);

    List<ExportingBillDto> toFromExportingbillDto(List<ExportbillEntity> exportingBillDto);
}
