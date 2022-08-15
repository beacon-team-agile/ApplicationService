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

//    @Transactional
//    @Test
//    public void testGetApplicationWorkFlowById_found() {
//        Integer id = applicationWorkFlowDao.addApplicationWorkFlow(mockApplicationWorkFlow);
//        assertNotNull(id);
//        assertEquals(mockApplicationWorkFlow,applicationWorkFlowDao.getApplicationWorkFlowById(id));
//        mockApplicationWorkFlow.setId(null);
//    }

    @Transactional
    @Test
    public void testGetApplicationWorkFlowById_notFound() {
        assertNull(applicationWorkFlowDao.getApplicationWorkFlowById(-1));
    }
}
