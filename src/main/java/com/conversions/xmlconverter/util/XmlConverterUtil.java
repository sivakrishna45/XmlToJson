package com.conversions.xmlconverter.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.conversions.xmlconverter.constants.ErrorConstants;
import com.conversions.xmlconverter.constants.XmlConverterConstants;
import com.conversions.xmlconverter.exceptions.NoFileIdentifierException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XmlConverterUtil {

    public static String convertNodetoJson(JsonNode rootNode) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode caseFileNodes = rootNode.path(XmlConverterConstants.PAYLOAD).path(XmlConverterConstants.CASE_FILES);
        Iterator<JsonNode> caseFiles = caseFileNodes.elements();
        List<Map<String, Object>> records = new ArrayList<>();
        while (caseFiles.hasNext()) {
            JsonNode caseFile = caseFiles.next();
            Map<String, Object> jsonData = new LinkedHashMap<>();
            String fileIdentifier = caseFile.path(XmlConverterConstants.FILE_IDENTIFIER_STRING).asText();
            if (!fileIdentifier.isBlank()) {
                jsonData.put(XmlConverterConstants.FILE_IDENTIFIER_STRING, fileIdentifier);
                jsonData.put(XmlConverterConstants.LOAN_DATA, caseFile.path(XmlConverterConstants.LOAN_DATA));

                records.add(jsonData);
            } else {
                throw new NoFileIdentifierException(ErrorConstants.NO_FILE_ID);
            }
        }
        return jsonMapper.writeValueAsString(records);
    }

}
