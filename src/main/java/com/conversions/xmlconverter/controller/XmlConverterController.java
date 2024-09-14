package com.conversions.xmlconverter.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conversions.xmlconverter.service.XmlConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class XmlConverterController {

    @Autowired
    XmlConverterService xmlConverterService;

    @GetMapping(value = "/getData", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getData() throws IOException, JsonProcessingException {
        return xmlConverterService.getData();
    }

}
