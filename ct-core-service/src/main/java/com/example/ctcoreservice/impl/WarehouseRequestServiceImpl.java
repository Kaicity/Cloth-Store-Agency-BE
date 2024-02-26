package com.example.ctcoreservice.impl;

import com.example.ctcommon.util.WarehouseServiceRequest;
import com.example.ctcoremodel.ProductModel;
import com.example.ctcoremodel.ResponseModel;
import com.example.ctcoremodel.SupplierModel;
import com.example.ctcoreservice.services.IWarehouseRequestService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class WarehouseRequestServiceImpl implements IWarehouseRequestService {

    @Autowired
    private WarehouseServiceRequest storeServiceRequest;

    private <T> T getContentResponse(Object response, Class<T> classCore) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T result = mapper.convertValue(response, classCore);
        return result;
    }

    @Override
    public ResponseModel<List<ProductModel>> getAllProductModelFromWarehouseByIds(HttpServletRequest request, List<String> productIds) throws IOException {
        ResponseModel<List<ProductModel>> response = new ResponseModel();
        String url = "/api/v1/Food/getAllProductByIds";
        response = storeServiceRequest.post(url, productIds, response.getClass(), request);
        if (response.getStatus() == HttpStatus.SC_OK && response.getResult() != null) {
            ProductModel[] result = getContentResponse(response.getResult(), ProductModel[].class);
            response.setResult(Arrays.asList(result));
        }
        return response;
    }

    @Override
    public ResponseModel<List<SupplierModel>> getSupplierFromWarehouseByIds(HttpServletRequest request, List<String>supplierIds) throws IOException {
        ResponseModel<List<SupplierModel>> response = new ResponseModel();
        String url = "/api/v1/Supplier/seachSupplierByIds";
        response = storeServiceRequest.post(url, supplierIds, response.getClass(), request);
        if (response.getStatus() == HttpStatus.SC_OK && response.getResult() != null) {
            SupplierModel[] result = getContentResponse(response.getResult(), SupplierModel[].class);
            response.setResult(Arrays.asList(result));
        }
        return response;
    }
}
