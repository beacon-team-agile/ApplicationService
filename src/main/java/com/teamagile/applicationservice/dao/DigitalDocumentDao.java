package com.teamagile.applicationservice.dao;

import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;

import java.util.List;

public interface DigitalDocumentDao {
    Integer addDigitalDocument(DigitalDocument digitalDocument);

    public List<DigitalDocument> getAllDigitalDocument();
    DigitalDocument getDigitalDocumentById(Integer id);

    DigitalDocument updateDigitalDocumentById(Integer id,DigitalDocument digitalDocument);

    void deleteDigitalDocumentById(Integer id);
}
