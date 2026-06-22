package com.selection.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ErpClient {

    @Resource
    private RestTemplate restTemplate;

    @Value("${erp.base-url}")
    private String baseUrl;

    @Value("${erp.api-token}")
    private String apiToken;

    public Map<String, Object> createProductDraft(String asin, String countryCode, Map<String, Object> snapshotData) {
        String url = baseUrl + "/api/product/create-draft";
        HttpHeaders headers = buildHeaders();
        Map<String, Object> body = new HashMap<>();
        body.put("asin", asin);
        body.put("countryCode", countryCode);
        body.put("snapshotData", snapshotData);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
        return response.getBody();
    }

    public Map<String, Object> listSkus(int page, int pageSize) {
        String url = baseUrl + "/api/sku/list?page=" + page + "&pageSize=" + pageSize;
        HttpHeaders headers = buildHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, request, Map.class);
        return response.getBody();
    }

    public Map<String, Object> getUserInfo(Long userId) {
        String url = baseUrl + "/api/user/info?userId=" + userId;
        HttpHeaders headers = buildHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, request, Map.class);
        return response.getBody();
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);
        return headers;
    }
}
