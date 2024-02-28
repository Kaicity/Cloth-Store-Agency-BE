package com.example.ctcoreservice.services;

import com.example.ctcoremodel.CustomerModel;
import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.ResponseModel;
import com.example.ctcoremodel.SupplierModel;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface IWarehouseRequestService {

    ResponseModel<List<ProductModel>> getAllProductModelFromWarehouseByIds(HttpServletRequest request, List<String> productIds)
            throws IOException;

    ResponseModel<List<SupplierModel>> getSupplierFromWarehouseByIds(HttpServletRequest request, List<String>ids)
            throws IOException;

    ResponseModel<List<CustomerModel>>getCustomerModelFromWarehouseByIds(HttpServletRequest request, List<String> customerIds)  throws IOException;
}
