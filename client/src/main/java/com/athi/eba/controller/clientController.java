package com.athi.eba.controller;

import com.athi.eba.model.clientRequest;
import com.athi.eba.model.clientResponse;
import com.athi.eba.service.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("client")
public class clientController {
    @Autowired
    private com.athi.eba.service.clientService clientService;

    @GetMapping("/getApi")
    public clientResponse handleGetApi() {
        String url = "http://example.com/api/get";
        clientResponse response = clientService.callGetApi(url);
        return response;
    }

    @PostMapping("/postApi")
    public clientResponse handlePostApi(@RequestBody clientRequest request) {
        String url = "http://example.com/api/post";
        clientResponse response = clientService.callPostApi(url, request);
        return response;
    }
}
