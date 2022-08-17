package com.teamagile.applicationservice.dao.impl;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles(value = "test")
@SpringBootTest
@Transactional
public class ApplicationWorkFlowDaoImplTest {
    @Autowired
    ApplicationWorkFlowDaoImpl applicationWorkFlowDao;

    ApplicationWorkFlow mockApplicationWorkFlow;

    @BeforeEach
    public void setup() {
        mockApplicationWorkFlow = ApplicationWorkFlow
                .builder()
                .employee_id("62f9717fafd8a52a954a05fa")
                .create_date("2022-08-14")
                .last_modification_date("2022-08-15")
                .status(true)
                .comment("comment")
                .build();
    }

    @Test
    public void test_GetApplicationWorkFlowById_found() {
        Integer id = applicationWorkFlowDao.addApplicationWorkFlow(mockApplicationWorkFlow);
        assertNotNull(id);
        assertEquals(mockApplicationWorkFlow,applicationWorkFlowDao.getApplicationWorkFlowById(id));
        mockApplicationWorkFlow.setId(null);
    }

    @Test
    public void test_GetApplicationWorkFlowById_notFound() {
        assertNull(applicationWorkFlowDao.getApplicationWorkFlowById(-1));
    }

    @Test
    public void test_UpdateApplicationWorkFlowById_success() {
        Integer id = applicationWorkFlowDao.addApplicationWorkFlow(mockApplicationWorkFlow);
        assertNotNull(id);
        assertEquals(mockApplicationWorkFlow,applicationWorkFlowDao.getApplicationWorkFlowById(id));
        ApplicationWorkFlow newApplicationWorkFlow = ApplicationWorkFlow
                .builder()
                .id(mockApplicationWorkFlow.getId())
                .employee_id("62f9717fafd8a52a954a05fa")
                .create_date("2022-08-14")
                .last_modification_date("2022-08-16")
                .status(false)
                .comment("updated")
                .build();
        mockApplicationWorkFlow = applicationWorkFlowDao.updateApplicationWorkFlowById(id,newApplicationWorkFlow);
        assertEquals(mockApplicationWorkFlow,applicationWorkFlowDao.getApplicationWorkFlowById(id));
        mockApplicationWorkFlow.setId(null);
    }

    @Test
    public void test_UpdateApplicationWorkFlowById_NotFound() {
        assertNull(applicationWorkFlowDao.updateApplicationWorkFlowById(-1,mockApplicationWorkFlow));
    }

    @Test
    public void test_DeleteApplicationWorkFlowById_success() {
        Integer id = applicationWorkFlowDao.addApplicationWorkFlow(mockApplicationWorkFlow);
        assertNotNull(id);
        assertEquals(mockApplicationWorkFlow,applicationWorkFlowDao.getApplicationWorkFlowById(id));
        applicationWorkFlowDao.deleteApplicationWorkFlowById(id);
        assertNull(applicationWorkFlowDao.getApplicationWorkFlowById(id));
    }

}
