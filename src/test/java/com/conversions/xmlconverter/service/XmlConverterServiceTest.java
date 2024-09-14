package com.conversions.xmlconverter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.conversions.xmlconverter.exceptions.NoFileIdentifierException;
import com.conversions.xmlconverter.util.Reader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlConverterServiceTest {

    @Mock
    Reader reader;

    @InjectMocks
    XmlConverterService xmlConverterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // without this we will get NPE
        ReflectionTestUtils.setField(xmlConverterService, "fileName",
                "");
        ReflectionTestUtils.setField(xmlConverterService, "filePath",
                "");
    }

    @Test
    @DisplayName("Service getData Test for a sample XML")
    void testGetData() throws Exception {

        JsonNode rootNode = createData();

        String expectedJson = "[{\"FileIdentifier\":\"1234ABCD\",\"Datasets\":{\"Dataset\":{\"attrId\":\"1234\",\"attr1\":\"val1\",\"attr2\":\"val2\"}}}]";

        Mockito.when(reader.readXmlTree(Mockito.anyString(), Mockito.anyString())).thenReturn(rootNode);
        String jsonData = xmlConverterService.getData();

        assertEquals(expectedJson, jsonData);
    }

    private JsonNode createData() throws Exception {
        String xmlData = "<root>" +
                "   <PublishPayload>\r\n" + //
                "        <Casefiles>\r\n" + //
                "            <Casefile schemaVersion=\"1.0.0\">\r\n" + //
                "                <FileIdentifier>1234ABCD</FileIdentifier>\r\n" + //
                "                <Datasets>\r\n" + //
                "                    <Dataset>\r\n" + //
                "                        <attrId>1234</attrId>\r\n" + //
                "                        <attr1>val1</attr1>\r\n" + //
                "                        <attr2>val2</attr2>\r\n" + //
                "                    </Dataset>\r\n" + //
                "                </Datasets>\r\n" + //
                "            </Casefile>\r\n" + //
                "        </Casefiles>\r\n" + //
                "    </PublishPayload>" +
                "</root>";
        return new XmlMapper().readTree(xmlData);
    }

    @Test
    @DisplayName("Service getData Test for a sample XML with Empty Loan ID")
    void testGetDataWithEmptyFileID() throws Exception {

        JsonNode rootNode = createDataEmptyID();

        Mockito.when(reader.readXmlTree(Mockito.anyString(), Mockito.anyString())).thenReturn(rootNode);

        assertThrowsExactly(NoFileIdentifierException.class, () -> xmlConverterService.getData());

    }

    private JsonNode createDataEmptyID() throws Exception {
        String xmlData = "<root>" +
                "   <PublishPayload>\r\n" + //
                "        <Casefiles>\r\n" + //
                "            <Casefile schemaVersion=\"1.0.0\">\r\n" + //
                "                <FileIdentifier></FileIdentifier>\r\n" + //
                "                <Datasets>\r\n" + //
                "                    <Dataset>\r\n" + //
                "                        <attrId></attrId>\r\n" + //
                "                        <attr1>val1</attr1>\r\n" + //
                "                        <attr2>val2</attr2>\r\n" + //
                "                    </Dataset>\r\n" + //
                "                </Datasets>\r\n" + //
                "            </Casefile>\r\n" + //
                "        </Casefiles>\r\n" + //
                "    </PublishPayload>" +
                "</root>";
        return new XmlMapper().readTree(xmlData);
    }

}
