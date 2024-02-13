package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.response.TypePaymentReceiptDto;
import com.example.ctapi.mappers.ITypePaymentReceiptMapper;
import com.example.ctapi.services.ITypePaymentReceiptService;
import com.example.ctcommon.enums.TypePaymentReceipt;
import com.example.ctcommondal.entity.TypePaymentReceiptEntity;
import com.example.ctcommondal.repository.ITypePaymentReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITypePaymentReceiptServiceImpl implements ITypePaymentReceiptService {
    private final Logger logger = LoggerFactory.getLogger(IReceiptServiceImpl.class);

    @Autowired
    private ITypePaymentReceiptRepository iTypePaymentReceiptRepository;

    @Transactional
    @Override
    public void addTypePaymentReceipt(TypePaymentReceiptDto typePaymentReceiptDto) {
        try {
            TypePaymentReceiptEntity typePaymentReceiptEntity = ITypePaymentReceiptMapper
                    .INSTANCE.toFromTypePaymentReceiptDto(typePaymentReceiptDto);
            System.out.println(typePaymentReceiptDto);
            System.out.println(typePaymentReceiptEntity);
            iTypePaymentReceiptRepository.save(typePaymentReceiptEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public List<TypePaymentReceiptDto> getAllPayments(TypePaymentReceipt typePaymentReceipt) {
        try {
            List<TypePaymentReceiptEntity> typePaymentReceiptEntities = iTypePaymentReceiptRepository.findAllTypePaymentReceipt(typePaymentReceipt);
            List<TypePaymentReceiptDto> typePaymentReceiptDtos = ITypePaymentReceiptMapper.INSTANCE.toFromTypePaymentReceiptsEntity(typePaymentReceiptEntities);
            System.out.println(typePaymentReceiptDtos);
            return typePaymentReceiptDtos;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    @Override
    public List<TypePaymentReceiptDto> getAllReceipt(TypePaymentReceipt typePaymentReceipt) {
        try {
            List<TypePaymentReceiptEntity> typePaymentReceiptEntities = iTypePaymentReceiptRepository.findAllTypePaymentReceipt(typePaymentReceipt);
            List<TypePaymentReceiptDto> typePaymentReceiptDtos = ITypePaymentReceiptMapper.INSTANCE.toFromTypePaymentReceiptsEntity(typePaymentReceiptEntities);
            System.out.println(typePaymentReceiptDtos);
            return typePaymentReceiptDtos;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
