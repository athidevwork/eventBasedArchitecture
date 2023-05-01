package com.athi.eba.service;

import com.athi.eba.model.clientRequest;
import com.athi.eba.model.clientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class clientService {
    @Autowired
    private RestTemplate restTemplate;

    public clientResponse callGetApi(String url) {
        clientResponse response = restTemplate.getForObject(url, clientResponse.class);
        return response;
    }

    public clientResponse callPostApi(String url, clientRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<clientRequest> entity = new HttpEntity<>(request, headers);
        clientResponse response = restTemplate.postForObject(url, entity, clientResponse.class);
        return response;
    }
}
