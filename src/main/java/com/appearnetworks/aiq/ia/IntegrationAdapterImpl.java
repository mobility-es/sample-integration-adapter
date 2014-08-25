package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.manager.TrainDamageReportManager;
import com.appearnetworks.aiq.ia.manager.TrainManager;
import com.appearnetworks.aiq.ia.manager.exception.NotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import com.appearnetworks.aiq.integrationframework.integration.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class overrides many methods on integration adapter base to provide custom logic as required by the application
 * based on the business domain. All data communication between the devices and the integration adapter takes place
 * through the server. The server is configured to reach the integration adapter through this endpoint.
 *
 * The protocol between the server and integration adapter is well defined and is used by extending the relevant
 * interfaces.
 */
@Component
public class IntegrationAdapterImpl extends IntegrationAdapterBase {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(IntegrationAdapterImpl.class);

    @Autowired
    private TrainManager trainManager;

    @Autowired
    private TrainDamageReportManager trainDamageReportManager;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * This method should be implemented for all cases where data is to be pulled from the integration adapter into the
     * devices.
     *
     * The server calls this method to get a list of document references of the documents that are to be sent to the
     * user. It is left up to the integration adapter to decide which data is to be sent to the users. Different
     * cases can exists e.g.
     *
     * 1). Send data to a particular user.
     * 3). Send data to everyone.
     *
     * In this implementation we neglect the userId and return the same set of document references for all
     * calls to this method.
     *
     * @param userId the userId of the AIQ server user.
     * @return a list of document references.
     */
    @Override
    public Collection<DocumentReference> findByUser(String userId) {
        List<DocumentReference> documentReferences = new ArrayList<>();

        documentReferences.addAll(fetchAllTrains());
        documentReferences.addAll(fetchAllTrainDamageReports());

        return documentReferences;
    }

    /**
     * This method is called by AIQ server to get the document corresponding to a docType and docId. The server
     * knows the docType and docId of documents due to the call to #findByUser().
     *
     * @param docType the document type of the document to be retrieved.
     * @param docId the id of the document to be retrieved.
     * @return the document
     */
    @Override
    public Object retrieveDocument(String docType, String docId) {
        switch (docType) {
            case Train.DOC_TYPE:
                return fetchTrainDocument(docId);
            case TrainDamageReport.DOC_TYPE:
                return fetchTrainDamageReportDocument(docId);
            default:
                return super.retrieveDocument(docType, docId);
        }
    }

    /**
     * This method is called by AIQ server to insert a document in the integration adapter.
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
        switch (docRef._type){
            case TrainDamageReport.DOC_TYPE:
                TrainDamageReport trainDamageReport;
                trainDamageReport = mapper.convertValue(doc, TrainDamageReport.class);
                return trainDamageReportManager.create(trainDamageReport).get_rev();

            case Train.DOC_TYPE:
                throw new UpdateException(HttpStatus.METHOD_NOT_ALLOWED);

            default:
                return super.insertDocument(userId, deviceId, docRef, doc);
        }
    }

    @Override
    public COMessageResponse processMessage(String destination, COMessage message, FileItemIterator attachments)
            throws UnavailableException, IOException, FileUploadException {
        LOG.info("CO message to " + destination + ": " + message.toString());
        while (attachments.hasNext()) {
            FileItemStream attachment = attachments.next();
            LOG.info("with attachment " + attachment.getFieldName() + " of type " + attachment.getContentType());
            FileUtils.copyInputStreamToFile(attachment.openStream(), File.createTempFile("attachment", "name"));
        }

        ObjectNode response = mapper.createObjectNode();

        switch (destination) {
            case "success":
                response.put("status", "success");
                return new COMessageResponse(true, response, 0, false, null, false, false);

            case "failure":
                return new COMessageResponse(false, null, 0, false, null, false, false);

            default:
                return super.processMessage(destination, message, attachments);
        }
    }

    private List<DocumentReference> fetchAllTrainDamageReports() {
        List<TrainDamageReport> trainDamageReports = trainDamageReportManager.getAll();
        List<DocumentReference> documentReferences = new ArrayList<>();

        for (TrainDamageReport damageReport : trainDamageReports) {
            documentReferences.add(new DocumentReference(damageReport));
        }

        return documentReferences;
    }

    private List<DocumentReference> fetchAllTrains() {
        List<Train> trains = trainManager.getAll();
        List<DocumentReference> documentReferenceList = new ArrayList<>();

        for (Train train : trains) {
            documentReferenceList.add(new DocumentReference(train));
        }

        return documentReferenceList;
    }

    private TrainDamageReport fetchTrainDamageReportDocument(String docId) {
        try {
            return trainDamageReportManager.find(docId);
        } catch (NotFoundException e) {
            LOG.warn(e.toString());
        }

        return null;
    }

    private Train fetchTrainDocument(String docId) {
        try {
            return trainManager.find(docId);
        } catch (NotFoundException e) {
            LOG.warn(e.toString());
        }

        return null;
    }
}
