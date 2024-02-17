package com.example.ctapi.mappers;

import com.example.ctapi.dtos.response.TypePaymentReceiptDto;
import com.example.ctcommondal.entity.TypePaymentReceiptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ITypePaymentReceiptMapper {
    ITypePaymentReceiptMapper INSTANCE = Mappers.getMapper(ITypePaymentReceiptMapper.class);

    @Mapping(target = "type", source = "type")
    TypePaymentReceiptEntity toFromTypePaymentReceiptDto(TypePaymentReceiptDto typePaymentReceiptDto);

    @Mapping(target = "type",source = "type")
    TypePaymentReceiptDto toFromTypePaymentReceiptEntity(TypePaymentReceiptEntity typePaymentReceiptEntity);

    List<TypePaymentReceiptDto> toFromTypePaymentReceiptsEntity(List<TypePaymentReceiptEntity> typePaymentReceiptEntity);

}
