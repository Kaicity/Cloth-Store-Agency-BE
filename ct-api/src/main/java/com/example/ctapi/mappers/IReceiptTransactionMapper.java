package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ReceiptTransactionDto;
import com.example.ctcommondal.entity.ReceiptTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface IReceiptTransactionMapper {
    IReceiptTransactionMapper INSTANCE = Mappers.getMapper(IReceiptTransactionMapper.class);

    @Mapping(target = "idReceipt", source = "receipt.id")
    ReceiptTransactionEntity toFromReceiptTransactionDto(ReceiptTransactionDto receiptTransactionDto);

    List<ReceiptTransactionEntity> toFromReceiptTransactionDtoList(List<ReceiptTransactionDto> receiptTransactionDto);


    @Mapping(target = "receipt.id", source = "idReceipt")
    ReceiptTransactionDto toFromPaymentTransactionEntity(ReceiptTransactionEntity receiptTransactionEntities);

    List<ReceiptTransactionDto> toFromReceiptTransactionEntityList(List<ReceiptTransactionEntity> receiptTransactionEntities);
}
