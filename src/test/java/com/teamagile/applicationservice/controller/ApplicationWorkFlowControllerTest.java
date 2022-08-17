package com.teamagile.applicationservice.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.SingleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import com.teamagile.applicationservice.service.ApplicationWorkFlowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationWorkFlowController.class)
@ExtendWith(MockitoExtension.class)
@WithMockUser
public class ApplicationWorkFlowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    ApplicationWorkFlowService applicationWorkFlowService;

    ApplicationWorkFlow mockApplicationWorkFlow;

    @BeforeEach
    public void init() throws IllegalAccessException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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
    public void test_GetApplicationWorkFlow_success() throws Exception {

        when(applicationWorkFlowService.getApplicationWorkFlowById(1)).thenReturn(mockApplicationWorkFlow);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/applicationWorkFlow/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        SingleApplicationWorkFlowResponse singleApplicationWorkFlowResponse = new Gson().fromJson(result.getResponse().getContentAsString(), SingleApplicationWorkFlowResponse.class);
        ApplicationWorkFlow applicationWorkFlow = singleApplicationWorkFlowResponse.getApplicationWorkFlow();
        assertEquals(mockApplicationWorkFlow.toString(),applicationWorkFlow.toString());
    }

    @Test
    public void test_GetApplicationWorkFlow_WhenApplicationWorkFlow_NotFound() throws Exception {
        when(applicationWorkFlowService.getApplicationWorkFlowById(-1)).thenThrow(new DataNotFoundException("Application Work Flow Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/applicationWorkFlow/{id}",-1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseStatus responseStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ResponseStatus.class);
        assertFalse(responseStatus.is_success());
        assertNotNull(responseStatus.getMessage());
    }

    @Test
    public void test_DeleteApplicationWorkFlow_WhenApplicationWorkFlow_NotFound() throws Exception {
        when(applicationWorkFlowService.getApplicationWorkFlowById(-1)).thenThrow(new DataNotFoundException("Application Work Flow Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/applicationWorkFlow/delete/{id}",-1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseStatus responseStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ResponseStatus.class);
        assertFalse(responseStatus.is_success());
        assertNotNull(responseStatus.getMessage());
    }

    @Test
    public void test_UpdateApplicationWorkFlow_WhenApplicationWorkFlow_NotFound() throws Exception {
        when(applicationWorkFlowService.getApplicationWorkFlowById(-1)).thenThrow(new DataNotFoundException("Application Work Flow Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/applicationWorkFlow/update/{id}",-1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(mockApplicationWorkFlow))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseStatus responseStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ResponseStatus.class);
        assertFalse(responseStatus.is_success());
    }
}
