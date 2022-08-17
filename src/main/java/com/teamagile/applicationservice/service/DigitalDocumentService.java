package com.teamagile.applicationservice.service;

import com.netflix.discovery.converters.Auto;
import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.dao.DigitalDocumentDao;
import com.teamagile.applicationservice.dao.impl.DigitalDocumentDaoImpl;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import com.teamagile.applicationservice.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DigitalDocumentService {

    public DigitalDocumentDao digitalDocumentDao;

    @Autowired
    public void setDigitalDocumentDao(DigitalDocumentDao digitalDocumentDao) {
        this.digitalDocumentDao = digitalDocumentDao;
    }

    public List<DigitalDocument> getAllDigitalDocument() {
        return digitalDocumentDao.getAllDigitalDocument();
    }

    public Integer addDigitalDocument(DigitalDocument digitalDocument) {
        return digitalDocumentDao.addDigitalDocument(digitalDocument);
    }

    public DigitalDocument getDigitalDocumentById(int id) {
        return Optional.ofNullable(digitalDocumentDao.getDigitalDocumentById(id))
                .orElseThrow(() -> new DataNotFoundException("Digital Document not found"));
    }

    public void deleteDigitalDocumentById(int id) {
        this.getDigitalDocumentById(id);
        digitalDocumentDao.deleteDigitalDocumentById(id);
    }

    public DigitalDocument updateDigitalDocumentById(int id, DigitalDocument digitalDocument) {
        this.getDigitalDocumentById(id);
        return digitalDocumentDao.updateDigitalDocumentById(id,digitalDocument);
    }
}
