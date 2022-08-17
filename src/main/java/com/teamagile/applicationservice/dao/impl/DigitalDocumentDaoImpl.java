package com.teamagile.applicationservice.dao.impl;

import com.teamagile.applicationservice.dao.AbstractHibernateDAO;
import com.teamagile.applicationservice.dao.ApplicationWorkFlowDao;
import com.teamagile.applicationservice.dao.DigitalDocumentDao;
import com.teamagile.applicationservice.domain.entity.ApplicationWorkFlow;
import com.teamagile.applicationservice.domain.entity.DigitalDocument;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DigitalDocumentDaoImpl extends AbstractHibernateDAO<DigitalDocument> implements DigitalDocumentDao {

    public DigitalDocumentDaoImpl() {
        setClazz(DigitalDocument.class);
    }
    @Override
    public Integer addDigitalDocument(DigitalDocument digitalDocument) {
        return add(digitalDocument);
    }

    @Override
    public DigitalDocument getDigitalDocumentById(Integer id) {
        return findById(id);
    }

    @Override
    public List<DigitalDocument> getAllDigitalDocument() {
        return getAll();
    }
    @Override
    public DigitalDocument updateDigitalDocumentById(Integer id, DigitalDocument digitalDocument) {
        Session session = getCurrentSession();
        DigitalDocument old_digitalDocument = findById(id);

        if(old_digitalDocument==null) {
            return null;
        }

        old_digitalDocument.setPath(digitalDocument.getPath());
        old_digitalDocument.set_required(digitalDocument.is_required());
        old_digitalDocument.setDescription(digitalDocument.getDescription());
        old_digitalDocument.setTitle(digitalDocument.getTitle());
        old_digitalDocument.setType(digitalDocument.getType());

        session.update(old_digitalDocument);

        return old_digitalDocument;

    }

    @Override
    public void deleteDigitalDocumentById(Integer id) {
        delete(id);
    }
}
