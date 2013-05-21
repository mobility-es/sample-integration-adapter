package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapterBase;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class IntegrationAdapterImpl extends IntegrationAdapterBase {
    private static Logger LOG = Logger.getLogger(IntegrationAdapterImpl.class.getName());

    @Autowired
    private TrainDao trainDao;

    @Override
    public List<DocumentReference> findByUserAndDevice(String userId, String deviceId) {
        List<DocumentReference> documentReferences = new ArrayList<>();

        try{
            documentReferences.addAll(getAllTrains());
        }catch (Exception e){
            LOG.log(Level.WARNING, "Unexpected exception when retrieving documents", e);
            throw e;
        }

        return documentReferences;
    }

    private List<DocumentReference> getAllTrains() {
        List<TrainDO> trainDOList = trainDao.getAll();
        List<DocumentReference> documentReferenceList = new ArrayList<>();

        for (TrainDO trainDO : trainDOList) {
            documentReferenceList.add(new DocumentReference(Train.DOC_ID_PREFIX + trainDO.getId(),
                                                                        Train.DOC_TYPE,
                                                                        trainDO.getRev()));
        }

        return documentReferenceList;
    }

    @Override
    public ObjectNode retrieveDocument(String docType, String docId) {
        switch (docType){
            case Train.DOC_TYPE:
                return getTrainDocument(docId);
            default:
                return super.retrieveDocument(docType, docId);
        }
    }

    private ObjectNode getTrainDocument(String docId){
        try {
            TrainDO trainDO = trainDao.find(docId.substring(Train.DOC_ID_PREFIX.length()));
            ObjectMapper mapper = new ObjectMapper();

            return mapper.valueToTree(trainDO);
        } catch (NoSuchDataObjectException e) {
            LOG.log(Level.WARNING, "Train with id:" + docId + " not found.", e);
        }

        return null;
    }
}
