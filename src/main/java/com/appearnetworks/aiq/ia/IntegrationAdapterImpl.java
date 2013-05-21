package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.manager.TrainDamageReportManager;
import com.appearnetworks.aiq.ia.dataaccess.manager.TrainManager;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
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
    private TrainManager trainManager;
    @Autowired
    private TrainDamageReportManager trainDamageReportManager;

    @Override
    public List<DocumentReference> findByUserAndDevice(String userId, String deviceId) {
        List<DocumentReference> documentReferences = new ArrayList<>();

        try{
            documentReferences.addAll(fetchAllTrains());
            documentReferences.addAll(fetchAllTrainDamageReports());
        }catch (Exception e){
            LOG.log(Level.WARNING, "Unexpected exception when retrieving documents", e);
            throw e;
        }

        return documentReferences;
    }

    private List<DocumentReference> fetchAllTrainDamageReports() {
        List<TrainDamageReport> trainDamageReports = trainDamageReportManager.getAll();
        List<DocumentReference> documentReferenceList = new ArrayList<>();

        for (TrainDamageReport damageReport : trainDamageReports) {
            documentReferenceList.add(new DocumentReference(TrainDamageReport.DOC_ID_PREFIX + damageReport.get_id(),
                                                            TrainDamageReport.DOC_TYPE,
                                                            damageReport.get_rev()));
        }

        return documentReferenceList;
    }

    private List<DocumentReference> fetchAllTrains() {
        List<Train> trains = trainManager.getAll();
        List<DocumentReference> documentReferenceList = new ArrayList<>();

        for (Train train : trains) {
            documentReferenceList.add(new DocumentReference(Train.DOC_ID_PREFIX + train.get_id(),
                                                                        Train.DOC_TYPE,
                                                                        train.get_rev()));
        }

        return documentReferenceList;
    }

    @Override
    public ObjectNode retrieveDocument(String docType, String docId) {
        switch (docType){
            case Train.DOC_TYPE:
                return fetchTrainDocument(docId);
            case TrainDamageReport.DOC_TYPE:
                return fetchTrainDamageReportDocument(docId);
            default:
                return super.retrieveDocument(docType, docId);
        }
    }

    private ObjectNode fetchTrainDamageReportDocument(String docId) {
        try {
            TrainDamageReport trainDamageReport = trainDamageReportManager.find(docId.substring(TrainDamageReport.DOC_ID_PREFIX.length()));
            ObjectMapper mapper = new ObjectMapper();

            return mapper.valueToTree(trainDamageReport);
        } catch (NoSuchDataObjectException e) {
            LOG.log(Level.WARNING, "Train damage report with id:" + docId + " not found.", e);
        }

        return null;
    }

    private ObjectNode fetchTrainDocument(String docId){
        try {
            Train train = trainManager.find(docId.substring(Train.DOC_ID_PREFIX.length()));
            ObjectMapper mapper = new ObjectMapper();

            return mapper.valueToTree(train);
        } catch (NoSuchDataObjectException e) {
            LOG.log(Level.WARNING, "Train with id:" + docId + " not found.", e);
        }

        return null;
    }
}
