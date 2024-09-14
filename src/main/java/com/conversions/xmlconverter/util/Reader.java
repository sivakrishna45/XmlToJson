package com.conversions.xmlconverter.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public class Reader {

    public JsonNode readXmlTree(String fileName, String filePath) throws IOException {
        if (!filePath.endsWith("/"))
            filePath += "/";
        File file = new File(filePath + fileName);
        return new XmlMapper().readTree(file);
    }
}
