package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.ReceiptDto;
import com.example.ctcommondal.entity.ReceiptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IReceiptMapper {
    IReceiptMapper INSTANCE = Mappers.getMapper(IReceiptMapper.class);

    @Mapping(target = "idTypeReceipt",source = "typePaymentReceipt.id")
    @Mapping(target = "status",source = "status")
    ReceiptEntity toFromReceiptDto(ReceiptDto receiptDto);

    @Mapping(target = "typePaymentReceipt.id",source = "idTypeReceipt")
    @Mapping(target = "status",source = "status")
    ReceiptDto toFromReceiptEntity(ReceiptEntity receiptEntity);
    List<ReceiptDto> toFromReceiptEntityList(List<ReceiptEntity> receiptEntityList);
}
