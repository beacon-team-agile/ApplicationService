package com.teamagile.applicationservice.controller;

import com.google.gson.Gson;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.SingleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.DigitalDocumentResponse.SingleDigitalDocumentResponse;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import com.teamagile.applicationservice.service.ApplicationWorkFlowService;
import com.teamagile.applicationservice.service.DigitalDocumentService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DigitalDocumentController.class)
@ExtendWith(MockitoExtension.class)
@WithMockUser
public class DigitalDocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    DigitalDocumentService digitalDocumentService;

    DigitalDocument mockDigitalDocument;

    @BeforeEach
    public void init() throws IllegalAccessException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockDigitalDocument = DigitalDocument.builder()
                .id(1)
                .type("type_test")
                .path("path_test")
                .is_required(false)
                .description("description_test")
                .title("title_test")
                .build();
    }

    @Test
    public void test_GetDigitalDocument_success() throws Exception {

        when(digitalDocumentService.getDigitalDocumentById(1)).thenReturn(mockDigitalDocument);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/digitalDocument/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        SingleDigitalDocumentResponse singleApplicationWorkFlowResponse = new Gson().fromJson(result.getResponse().getContentAsString(), SingleDigitalDocumentResponse.class);
        DigitalDocument digitalDocument = singleApplicationWorkFlowResponse.getDigitalDocument();
        assertEquals(mockDigitalDocument.toString(),digitalDocument.toString());
    }

    @Test
    public void test_GetDigitalDocument_WhenDigitalDocument_NotFound() throws Exception {
        when(digitalDocumentService.getDigitalDocumentById(-1)).thenThrow(new DataNotFoundException("Application Work Flow Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/digitalDocument/{id}",-1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseStatus responseStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ResponseStatus.class);
        assertFalse(responseStatus.is_success());
        assertNotNull(responseStatus.getMessage());
    }

    @Test
    public void test_DeleteDigitalDocument_WhenDigitalDocument_NotFound() throws Exception {
        when(digitalDocumentService.getDigitalDocumentById(-1)).thenThrow(new DataNotFoundException("Application Work Flow Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/digitalDocument/delete/{id}",-1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseStatus responseStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ResponseStatus.class);
        assertFalse(responseStatus.is_success());
        assertNotNull(responseStatus.getMessage());
    }

    @Test
    public void test_UpdateApplicationWorkFlow_WhenApplicationWorkFlow_NotFound() throws Exception {
        when(digitalDocumentService.getDigitalDocumentById(-1)).thenThrow(new DataNotFoundException("Application Work Flow Not Found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/digitalDocument/update/{id}",-1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(mockDigitalDocument))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseStatus responseStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ResponseStatus.class);
        assertFalse(responseStatus.is_success());
    }
}
