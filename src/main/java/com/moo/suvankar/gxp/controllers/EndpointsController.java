package com.moo.suvankar.gxp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EndpointsController {

    @GetMapping("/endpoints")
    public List<String> getEndpoints() {
        return Arrays.asList(
                "/api/v1/parse?file={file}",
                "/api/v1/definitions?word={word}",
                "/api/v1/endpoints");
    }
}
