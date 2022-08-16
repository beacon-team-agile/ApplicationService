package com.teamagile.applicationservice.service;

import com.teamagile.applicationservice.dao.impl.ApplicationWorkFlowDaoImpl;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationWorkFlowServiceTest {
    @InjectMocks
    ApplicationWorkFlowService applicationWorkFlowService;

    @Mock
    ApplicationWorkFlowDaoImpl applicationWorkFlowDao;

    ApplicationWorkFlow mockApplicationWorkFlow;

    @BeforeEach
    public void setup() {
        mockApplicationWorkFlow = ApplicationWorkFlow.builder()
                .id(1)
                .employee_id("62fa71b93f8180055bd111a5")
                .create_date("2022-08-14")
                .last_modification_date("2022-08-15")
                .status(true)
                .comment("comment")
                .build();
    }

    @Test
    public void test_GetApplicationWorkFlowById_success() {
        when(applicationWorkFlowDao.getApplicationWorkFlowById(1)).thenReturn(mockApplicationWorkFlow);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDao.getApplicationWorkFlowById(1);
        assertEquals(mockApplicationWorkFlow,applicationWorkFlow);
    }

    @Test
    public void  test_GetApplicationWorkFlowById_failed() {
        when(applicationWorkFlowDao.getApplicationWorkFlowById(-1)).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> applicationWorkFlowService.getApplicationWorkFlowById(-1));
    }
}
