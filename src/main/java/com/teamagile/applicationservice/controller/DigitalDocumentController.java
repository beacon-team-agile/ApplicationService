package com.teamagile.applicationservice.controller;

import com.netflix.discovery.converters.Auto;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.AddApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.MultipleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.ApplicationWorkFlowResponse.SingleApplicationWorkFlowResponse;
import com.teamagile.applicationservice.domain.response.DigitalDocumentResponse.AddDigitalDocumentResponse;
import com.teamagile.applicationservice.domain.response.DigitalDocumentResponse.MultipleDigitalDocumentResponse;
import com.teamagile.applicationservice.domain.response.DigitalDocumentResponse.SingleDigitalDocumentResponse;
import com.teamagile.applicationservice.domain.response.common.ResponseStatus;
import com.teamagile.applicationservice.service.DigitalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/digitalDocument")
public class DigitalDocumentController {
    private DigitalDocumentService digitalDocumentService;

    @Autowired
    public void setDigitalDocumentService(DigitalDocumentService digitalDocumentService) {
        this.digitalDocumentService = digitalDocumentService;
    }

    @GetMapping("/all")
    public MultipleDigitalDocumentResponse getAllDigitalDocument() {
        List<DigitalDocument> digitalDocumentList = digitalDocumentService.getAllDigitalDocument();
        return MultipleDigitalDocumentResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Successfully got all the Application Work Flows")
                                .build()
                )
                .digitalDocumentList(digitalDocumentList)
                .build();
    }

    @PostMapping("/add")
    public AddDigitalDocumentResponse addDigitalDocument(@Valid @RequestBody DigitalDocument digitalDocument) {
        DigitalDocument newDocument = DigitalDocument.builder()
                .type(digitalDocument.getType())
                .is_required(digitalDocument.is_required())
                .path(digitalDocument.getPath())
                .description(digitalDocument.getDescription())
                .title(digitalDocument.getTitle())
                .build();

        Integer id = digitalDocumentService.addDigitalDocument(newDocument);

        return AddDigitalDocumentResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Successfully added new Application Work Flow!")
                                .build()
                )
                .id(id)
                .digitalDocument(newDocument)
                .build();
    }

    @GetMapping("/{id}")
    public SingleDigitalDocumentResponse getDigitalDocumentById(@PathVariable Integer id) {
        DigitalDocument digitalDocument = digitalDocumentService.getDigitalDocumentById(id);

        return SingleDigitalDocumentResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Found ApplicationWorkFlow")
                                .build()
                )
                .digitalDocument(digitalDocument)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public SingleDigitalDocumentResponse deleteDigitalDocumentById(@PathVariable Integer id) {
        DigitalDocument digitalDocument = digitalDocumentService.getDigitalDocumentById(id);

        digitalDocumentService.deleteDigitalDocumentById(id);

        return SingleDigitalDocumentResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Deleted ApplicationWorkFlow")
                                .build()
                )
                .digitalDocument(digitalDocument)
                .build();
    }

    @PatchMapping("/update/{id}")
    public SingleDigitalDocumentResponse deleteDigitalDocumentById(@PathVariable Integer id,
                                                                           @RequestBody DigitalDocument digitalDocument) {
        DigitalDocument updated_digitalDocument = digitalDocumentService.updateDigitalDocumentById(id,digitalDocument);

        return SingleDigitalDocumentResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Updated ApplicationWorkFlow")
                                .build()
                )
                .digitalDocument(updated_digitalDocument)
                .build();

    }
}
