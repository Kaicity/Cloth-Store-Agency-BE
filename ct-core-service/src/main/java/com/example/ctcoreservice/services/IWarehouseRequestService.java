package com.example.ctcoreservice.services;

import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.ResponseModel;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface IWarehouseRequestService {

    ResponseModel<List<ProductModel>> getAllProductModelFromWarehouseByIds(HttpServletRequest request, List<String> productIds)
            throws IOException;
}
