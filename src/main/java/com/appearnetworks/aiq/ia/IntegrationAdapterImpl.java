package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.manager.TrainDamageReportManager;
import com.appearnetworks.aiq.ia.manager.TrainManager;
import com.appearnetworks.aiq.ia.manager.exception.TrainDamageReportNotFoundException;
import com.appearnetworks.aiq.ia.manager.exception.TrainNotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapterBase;
import com.appearnetworks.aiq.integrationframework.integration.UpdateException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of <code>IntegrationAdapterBase</code>. This class overrides many methods on integration adapter
 * base to provide custom logic as required by the application based on the business domain. All data communication
 * between the devices and the integration adapter takes place through the platform. The platform is configured to
 * reach the integration adapter through this endpoint.
 *
 * The protocol between the platform and integration adapter is well defined is used the extending the relevant
 * interfaces.
 *
 */
@Component
public class IntegrationAdapterImpl extends IntegrationAdapterBase {
    private static Logger LOG = Logger.getLogger(IntegrationAdapterImpl.class.getName());

    @Autowired
    private TrainManager trainManager;
    @Autowired
    private TrainDamageReportManager trainDamageReportManager;

    /**
     * This method should be implemented for all cases where data is to be pulled from the integration adapter into the
     * devices.
     *
     * The platform calls this method to get a list of document references of the documents that are to be sent to the
     * user. It is left up to the integration adapter to decide which data is to be sent to the devices. Different
     * cases can exists e.g.
     *
     * 1). Send data to a particular user and device.
     * 2). Send data to all devices of a particular user.
     * 3). Send data to everyone.
     *
     * In this implementation we neglect the userId and deviceId and return the same set of document references for all
     * calls to this method.
     *
     * @param userId the userId of the AIQ Platform user.
     * @param deviceId the deviceId of the AIQ Platform user.
     * @return a list of document references.
     */
    @Override
    public List<DocumentReference> findByUserAndDevice(String userId, String deviceId) {
        List<DocumentReference> documentReferences = new ArrayList<>();

        try {
            documentReferences.addAll(fetchAllTrains());
            documentReferences.addAll(fetchAllTrainDamageReports());
        } catch (Exception e){
            LOG.log(Level.WARNING, "Unexpected exception when retrieving documents", e);
            throw e;
        }

        return documentReferences;
    }

    /**
     * This method is called by AIQ platform to get the document corresponding to a docType and docId. The platform
     * knows the docType and docId of documents due to the call to #findByUserAndDevices().
     *
     * @param docType the document type of the document to be retrieved.
     * @param docId the id of the docment to be retrieved.
     * @return the actual document as ObjectNode.
     */
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

    /**
     * This method is called by AIQ platform to insert a document in the integration adapter.
     *
     * @param userId the userId of the user who created the document.
     * @param deviceId the deviceId of the device that created the document.
     * @param docRef the document reference of the document to be created.
     * @param doc the actual document.
     * @return The return call should be the revision of the newly created document.
     *
     * @throws UpdateException thrown when there is an issue inserting the document.
     */
    @Override
    public long insertDocument(String userId, String deviceId, DocumentReference docRef, ObjectNode doc) throws UpdateException {
        ObjectMapper mapper = new ObjectMapper();

        switch (docRef._type){
            case TrainDamageReport.DOC_TYPE:
                TrainDamageReport trainDamageReport;
                try {
                    trainDamageReport = mapper.readValue(doc, TrainDamageReport.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return trainDamageReportManager.create(trainDamageReport).get_rev();

            default:
                return super.insertDocument(userId, deviceId, docRef, doc);
        }
    }

    private List<DocumentReference> fetchAllTrainDamageReports() {
        List<TrainDamageReport> trainDamageReports = trainDamageReportManager.getAll();
        List<DocumentReference> documentReferences = new ArrayList<>();

        for (TrainDamageReport damageReport : trainDamageReports) {
            documentReferences.add(new DocumentReference(TrainDamageReport.DOC_ID_PREFIX + damageReport.get_id(),
                    TrainDamageReport.DOC_TYPE,
                    damageReport.get_rev()));
        }

        return documentReferences;
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

    private ObjectNode fetchTrainDamageReportDocument(String docId) {
        try {
            TrainDamageReport trainDamageReport = trainDamageReportManager.find(docId.substring(TrainDamageReport.DOC_ID_PREFIX.length()));
            ObjectMapper mapper = new ObjectMapper();

            return mapper.valueToTree(trainDamageReport);
        } catch (TrainDamageReportNotFoundException e) {
            LOG.log(Level.WARNING, e.toString());
        }

        return null;
    }

    private ObjectNode fetchTrainDocument(String docId){
        try {
            Train train = trainManager.find(docId.substring(Train.DOC_ID_PREFIX.length()));
            ObjectMapper mapper = new ObjectMapper();

            return mapper.valueToTree(train);
        } catch (TrainNotFoundException e) {
            LOG.log(Level.WARNING, e.toString());
        }

        return null;
    }
}
