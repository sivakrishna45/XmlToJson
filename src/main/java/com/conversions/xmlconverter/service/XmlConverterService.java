package com.conversions.xmlconverter.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.conversions.xmlconverter.util.Reader;
import com.conversions.xmlconverter.util.XmlConverterUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class XmlConverterService {

    @Value("${app.input.file.name}")
    private String fileName;

    @Value("${app.input.file.path}")
    private String filePath;

    @Autowired
    Reader reader;

    public String getData() throws JsonProcessingException, IOException {
        JsonNode rootNode = reader.readXmlTree(fileName, filePath);
        String jsonData = XmlConverterUtil.convertNodetoJson(rootNode);
        return jsonData;
    }
}
