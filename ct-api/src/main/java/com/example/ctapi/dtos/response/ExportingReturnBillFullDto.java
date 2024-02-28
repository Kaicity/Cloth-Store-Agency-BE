package com.example.ctapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportingReturnBillFullDto {
    ExportingReturnBillDto exportingReturnBill;
    List<ExportingReturnTransactionDto> exportingReturnTransactionList;
}
