package com.example.ctcommon.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.http.impl.client.CloseableHttpClient;
import java.io.IOException;

@Component
public class WarehouseServiceRequest {
    private String warehouseApiUrl="http://localhost:5556";

    public <T, K> T post(String uri, K body, Class<T> clazz, HttpServletRequest request)
            throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(warehouseApiUrl + uri);

            String authHeader = request.getHeader("Authorization");
            post.setHeader("Authorization", authHeader);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(body);
            StringEntity entry = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entry);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");

            CloseableHttpResponse response = httpClient.execute(createHost(), post);

            return getContentResponse(response, clazz);
        } catch (IOException e) {
            // Handle or log the exception appropriately
            throw e;
        }
    }

    private <T> T getContentResponse(CloseableHttpResponse response, Class<T> clazz)
            throws IOException {
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            String apiOutput = EntityUtils.toString(httpEntity);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T res = mapper.readValue(apiOutput, clazz);
            response.close();
            return res;
        }
        return null;
    }

    private HttpHost createHost() {
        return HttpHost.create(warehouseApiUrl);
    }
}