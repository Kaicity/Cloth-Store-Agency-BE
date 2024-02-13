package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ExportingBillDto;
import com.example.ctapi.dtos.response.PaymentDto;
import com.example.ctcommondal.entity.ExportbillEntity;
import com.example.ctcommondal.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPaymentMapper {
    IPaymentMapper INSTANCE = Mappers.getMapper(IPaymentMapper.class);

    @Mapping(target = "idTypePayment", source = "typePaymentReceipt.id")
    @Mapping(target = "status", source = "status")
    PaymentEntity toFromPaymentDto(PaymentDto paymentDto);

    @Mapping(target = "typePaymentReceipt.id",source = "idTypePayment")
    @Mapping(target = "status",source = "status")
    PaymentDto toFromPaymentEntity(PaymentEntity paymentEntity);

    List<PaymentDto> toFromPaymentEntityList(List<PaymentEntity> paymentEntityList);
}