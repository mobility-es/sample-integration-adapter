package com.appearnetworks.aiq.ia.integration;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapter;
import com.appearnetworks.aiq.integrationframework.integration.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "/META-INF/testApplicationContext.xml" })
public class IntegrationAdapterTest {

    @Autowired
    IntegrationAdapter integrationAdapter;

    @Autowired
    private TrainDao trainDao;


    private TrainDO testTrainDO;

    @Before
    public void setup(){
        trainDao.create(new TrainDO(2000L));
        testTrainDO = trainDao.getAll().get(0);
    }

    @Test
    public void whenTrainDocumentExistThenItCanBeRetrieved() throws IOException {
        List<DocumentReference> documentReferences = integrationAdapter.findByUser("userId");
        assertFalse(documentReferences.isEmpty());
        assertTrue(documentReferences.size() == 1);

        DocumentReference documentReference = documentReferences.get(0);
        assertEquals(Train.DOC_TYPE, documentReference._type);

        Train train = (Train)integrationAdapter.retrieveDocument(Train.DOC_TYPE, documentReference._id);

        assertNotNull(train);
        assertTrue(train.getNumber() == testTrainDO.getNumber());
    }

    @DirtiesContext
    @Test
    public void whenTrainDamageReportIsAddedThenItCanBeRetrieved() throws UpdateException, IOException {
        integrationAdapter.insertDocument("UserId",
                "DeviceId",
                new DocumentReference("", TrainDamageReport.DOC_TYPE, 0L),
                createTrainDamageReport(testTrainDO.getId()));

        List<TrainDamageReport> trainDamageReports = new ArrayList<>();

        List<DocumentReference> documentReferences = integrationAdapter.findByUser("userId");
        for (DocumentReference documentReference : documentReferences) {
            if(documentReference._type.equals(TrainDamageReport.DOC_TYPE)) {
                trainDamageReports.add((TrainDamageReport)integrationAdapter.retrieveDocument(documentReference._type, documentReference._id));
            }
        }

        assertFalse(trainDamageReports.isEmpty());
        assertTrue(trainDamageReports.size() == 1);

        TrainDamageReport createTrainDamageReport = trainDamageReports.get(0);
        assertNotNull(createTrainDamageReport);
        assertFalse(createTrainDamageReport.get_id().isEmpty());
        assertTrue(createTrainDamageReport.get_rev() == 1L);
    }

    private ObjectNode createTrainDamageReport(String trainId) throws IOException {
        return (ObjectNode) new ObjectMapper().readTree("{\n" +
                "   \"trainId\":\"" + trainId + "\",\n" +
                "   \"description\":\"Passenger train\",\n" +
                "   \"reportedBy\":\"Alex\",\n" +
                "   \"creationDateTime\":1369310662\n" +
                "}");
    }

}
