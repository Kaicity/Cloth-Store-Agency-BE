package com.example.ctapi.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearchDto<T> {
    private int currentPage;
    private int recordOfPage;
    private long totalRecords;
    private boolean sortAsc = true;
    private String sortBy;
    private int pagingRange;
    private T result;
}

