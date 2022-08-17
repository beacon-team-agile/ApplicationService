package com.teamagile.applicationservice.service;

import com.teamagile.applicationservice.dao.impl.ApplicationWorkFlowDaoImpl;
import com.teamagile.applicationservice.dao.impl.DigitalDocumentDaoImpl;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DigitalDocumentServiceTest {
    @InjectMocks
    DigitalDocumentService digitalDocumentService;

    @Mock
    DigitalDocumentDaoImpl digitalDocumentDao;

    DigitalDocument mockDigitalDocument;

    @BeforeEach
    public void setup() {
        mockDigitalDocument = DigitalDocument.builder()
                .id(1)
                .type("type_test")
                .path("path_test")
                .is_required(true)
                .description("description_test")
                .title("title_test")
                .build();
    }

    @Test
    public void test_GetDigitalDocumentById_success() {
        when(digitalDocumentDao.getDigitalDocumentById(1)).thenReturn(mockDigitalDocument);
        DigitalDocument digitalDocument = digitalDocumentDao.getDigitalDocumentById(1);
        assertEquals(mockDigitalDocument,digitalDocument);
    }

    @Test
    public void  test_GetDigitalDocumentById_failed() {
        when(digitalDocumentDao.getDigitalDocumentById(-1)).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> digitalDocumentService.getDigitalDocumentById(-1));
    }

    @Test
    public void test_DeleteDigitalDocumentById_NotFound() {
        assertThrows(DataNotFoundException.class, () -> digitalDocumentService.deleteDigitalDocumentById(-1));
    }

    @Test
    public void test_updateDigitalDocumentById_success() {
        DigitalDocument newDigitalDocument = DigitalDocument.builder()
                .id(mockDigitalDocument.getId())
                .type("type_test_up")
                .path("path_test_up")
                .is_required(true)
                .description("description_test_up")
                .title("title_test_up")
                .build();
        when(digitalDocumentDao.updateDigitalDocumentById(1,newDigitalDocument)).thenReturn(newDigitalDocument);
        assertEquals(newDigitalDocument,digitalDocumentDao.updateDigitalDocumentById(1,newDigitalDocument));
    }

    @Test
    public void test_updateDigitalDocumentById_NotFound() {
        assertThrows(DataNotFoundException.class, () -> digitalDocumentService.updateDigitalDocumentById(-1,mockDigitalDocument));
    }
}
