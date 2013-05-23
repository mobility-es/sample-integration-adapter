package com.appearnetworks.aiq.ia.integration;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.manager.TrainDamageReportManager;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/testApplicationContext.xml" })
public class IntegrationAdapterTest {

    @Autowired
    IntegrationAdapter integrationAdapter;

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainDamageReportManager trainDamageReportManager;

    @Before
    public void setup(){
        TrainDO trainDO = new TrainDO(2000L);
        trainDao.create(trainDO);
    }


    @Test
    public void addAndGetTrainDamageReport() throws UpdateException, IOException {

        integrationAdapter.insertDocument("UserId",
                "DeviceId",
                new DocumentReference("", TrainDamageReport.DOC_TYPE, 0L),
                createTrainDamageReport());
    }

    private ObjectNode createTrainDamageReport() throws IOException {
        return (ObjectNode) new ObjectMapper().readTree("{\n" +
                "   \"trainId\":\"123-1233\",\n" +
                "   \"description\":\"Passenger train\",\n" +
                "   \"reportedBy\":\"Alex\",\n" +
                "   \"creationDateTime\":1369310662\n" +
                "}");
    }

}
