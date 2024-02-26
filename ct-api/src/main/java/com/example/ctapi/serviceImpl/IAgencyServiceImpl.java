package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.AgencyDto;
import com.example.ctapi.mappers.IAgencyMapper;
import com.example.ctapi.services.IAgencyService;
import com.example.ctcommondal.entity.AgencyEntity;
import com.example.ctcommondal.repository.IAgencyRespository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class IAgencyServiceImpl implements IAgencyService {

    private final Logger logger = LoggerFactory.getLogger(IImportingServiceImpl.class);
    private final IAgencyRespository iAgencyRespository;

    @Override
    @Transactional
    public AgencyDto getAgencyById(String id){
        try {
            AgencyEntity agencyEntity = iAgencyRespository.findAgencyById(id);
            AgencyDto agencyDto = IAgencyMapper.INSTANCE.toFromAgencyEntity(agencyEntity);
            return agencyDto;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
