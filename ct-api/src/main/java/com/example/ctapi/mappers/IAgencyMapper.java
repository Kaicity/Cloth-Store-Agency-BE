package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.AgencyDto;
import com.example.ctcommondal.entity.AgencyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAgencyMapper {
    IAgencyMapper INSTANCE = Mappers.getMapper(IAgencyMapper.class);

    AgencyEntity toFromAgencyDto(AgencyDto agencyDtoDto);

    @Mapping(target = "company.id", source = "companyId")
    AgencyDto toFromAgencyEntity(AgencyEntity agencyEntity);
}
