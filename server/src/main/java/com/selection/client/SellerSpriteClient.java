package com.selection.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class SellerSpriteClient {

    @Resource
    private RestTemplate restTemplate;

    @Value("${seller-sprite.base-url}")
    private String baseUrl;

    @Value("${seller-sprite.api-key}")
    private String apiKey;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> fetchCategoryData(String categoryId, String countryCode) {
        String url = baseUrl + "/api/category/data?categoryId=" + categoryId + "&countryCode=" + countryCode;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", apiKey);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Map[].class);
        Map[] body = response.getBody();
        return body != null ? Arrays.asList(body) : List.of();
    }
}
