package com.appearnetworks.aiq.ia.integration;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapter;
import com.appearnetworks.aiq.integrationframework.integration.UpdateException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
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
    public void retrieveAddedTrainDocument() throws IOException {
        List<DocumentReference> documentReferences = integrationAdapter.findByUserAndDevice("userId", "DeviceId");
        assertFalse(documentReferences.isEmpty());
        assertTrue(documentReferences.size() == 1);

        DocumentReference documentReference = documentReferences.get(0);
        assertEquals(Train.DOC_TYPE, documentReference._type);

        Train train = new ObjectMapper().readValue(integrationAdapter.retrieveDocument(Train.DOC_TYPE, documentReference._id),
                                                   Train.class);
        assertNotNull(train);
        assertEquals(train.getNumber(), testTrainDO.getNumber());
    }

    @DirtiesContext
    @Test
    public void addAndGetTrainDamageReport() throws UpdateException, IOException {
        integrationAdapter.insertDocument("UserId",
                "DeviceId",
                new DocumentReference("", TrainDamageReport.DOC_TYPE, 0L),
                createTrainDamageReport(testTrainDO.getId()));

        List<TrainDamageReport> trainDamageReports = new ArrayList<>();

        List<DocumentReference> documentReferences = integrationAdapter.findByUserAndDevice("userId", "deviceId");
        for (DocumentReference documentReference : documentReferences) {
            if(documentReference._type.equals(TrainDamageReport.DOC_TYPE)) {
                trainDamageReports.add(new ObjectMapper().readValue(
                        integrationAdapter.retrieveDocument(documentReference._type, documentReference._id),
                        TrainDamageReport.class
                ));
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
