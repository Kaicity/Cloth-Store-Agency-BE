package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.PaymentTransactionDto;
import com.example.ctcommondal.entity.PaymentTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface IPaymentTransactionMapper {
    IPaymentTransactionMapper INSTANCE = Mappers.getMapper(IPaymentTransactionMapper.class);
    @Mapping(target = "idPayment",source = "payment.id")
    PaymentTransactionEntity toFromPaymentTransactionDtoList(PaymentTransactionDto paymentTransactionDto);

    List<PaymentTransactionEntity> toFromPaymentTransactionDtoList(List<PaymentTransactionDto> paymentTrasaction);
    @Mapping(target = "payment.id",source = "idPayment")
    PaymentTransactionDto toFromPaymentTransactionEntity(PaymentTransactionEntity paymentTransactionEntity);
    List<PaymentTransactionDto> toFromPaymentTransactionEntityList(List<PaymentTransactionEntity> paymentTransactionEntityList);
}
