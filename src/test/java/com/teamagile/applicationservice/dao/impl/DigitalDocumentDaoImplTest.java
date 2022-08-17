package com.teamagile.applicationservice.dao.impl;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles(value = "test")
@SpringBootTest
@Transactional
public class DigitalDocumentDaoImplTest {
    @Autowired
    DigitalDocumentDaoImpl digitalDocumentDao;

    DigitalDocument mockDigitalDocument;

    @BeforeEach
    public void setup() {
        mockDigitalDocument = DigitalDocument.builder()
                .type("type_test")
                .path("path_test")
                .is_required(true)
                .description("description_test")
                .title("title_test")
                .build();
    }

    @Test
    public void test_GetDigitalDocumentById_found() {
        Integer id = digitalDocumentDao.addDigitalDocument(mockDigitalDocument);
        assertNotNull(id);
        assertEquals(mockDigitalDocument,digitalDocumentDao.getDigitalDocumentById(id));
        mockDigitalDocument.setId(null);
    }

    @Test
    public void test_GetDigitalDocumentById_notFound() {
        assertNull(digitalDocumentDao.getDigitalDocumentById(-1));
    }

    @Test
    public void test_UpdateDigitalDocumentById_success() {
        Integer id = digitalDocumentDao.addDigitalDocument(mockDigitalDocument);
        assertNotNull(id);
        assertEquals(mockDigitalDocument,digitalDocumentDao.getDigitalDocumentById(id));

        DigitalDocument newDigitalDocument = DigitalDocument.builder()
                .id(mockDigitalDocument.getId())
                .type("type_test_up")
                .path("path_test_up")
                .is_required(true)
                .description("description_test_up")
                .title("title_test_up")
                .build();
        mockDigitalDocument = digitalDocumentDao.updateDigitalDocumentById(id,newDigitalDocument);
        assertEquals(mockDigitalDocument,digitalDocumentDao.getDigitalDocumentById(id));
        mockDigitalDocument.setId(null);
    }

    @Test
    public void test_UpdateDigitalDocumentById_NotFound() {
        assertNull(digitalDocumentDao.updateDigitalDocumentById(-1,mockDigitalDocument));
    }

    @Test
    public void test_DeleteApplicationWorkFlowById_success() {
        Integer id = digitalDocumentDao.addDigitalDocument(mockDigitalDocument);
        assertNotNull(id);
        assertEquals(mockDigitalDocument,digitalDocumentDao.getDigitalDocumentById(id));
        digitalDocumentDao.deleteDigitalDocumentById(id);
        assertNull(digitalDocumentDao.getDigitalDocumentById(id));
    }
}
