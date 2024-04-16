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
    @Mapping(target = "fullName",source = "customerNotLogin.fullName")
    @Mapping(target = "phone",source = "customerNotLogin.phone")
    @Mapping(target = "email",source = "customerNotLogin.email")
    @Mapping(target = "address",source = "customerNotLogin.address")
    ExportbillEntity toFromExportingbillEntity(ExportingBillDto exportingBillDto);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "agency.id", source = "agencyId")
    @Mapping(target = "dateExport", source = "dateExport")
    @Mapping(target = "customerNotLogin.eid",source = "eid")
    @Mapping(target = "customerNotLogin.fullName",source = "fullName")
    @Mapping(target = "customerNotLogin.phone",source = "phone")
    @Mapping(target = "customerNotLogin.email",source = "email")
    @Mapping(target = "customerNotLogin.address",source = "address")
    ExportingBillDto toFromExportingbillDto(ExportbillEntity exportingBillDto);

    List<ExportingBillDto> toFromExportingbillDto(List<ExportbillEntity> exportingBillDto);
}
