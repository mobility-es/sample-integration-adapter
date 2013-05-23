package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.manager.TrainDamageReportManager;
import com.appearnetworks.aiq.ia.dataaccess.manager.TrainManager;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapter;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class IntegrationAdapterTest {

    private IntegrationAdapter integrationAdapter;

    @Mock
    private TrainManager trainManagerMock;
    @Mock
    private TrainDamageReportManager trainDamageReportManagerMock;

    private Train train1;
    private Train train2;
    private TrainDamageReport trainDamageReport;

    @Before
    public void setup() {
        integrationAdapter = new IntegrationAdapterImpl();

        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(integrationAdapter, "trainManager", trainManagerMock);
        ReflectionTestUtils.setField(integrationAdapter, "trainDamageReportManager", trainDamageReportManagerMock);

        createTrains();
        createTrainDamageReport();
    }

    @Test
    public void listDocuments() {
        when(trainManagerMock.getAll()).thenReturn(getSomeTrains());
        when(trainDamageReportManagerMock.getAll()).thenReturn(getSomeTrainDamageReports());

        assertArrayEquals(new DocumentReference[]{
                new DocumentReference(Train.DOC_ID_PREFIX + train1.get_id(), Train.DOC_TYPE, 1L),
                new DocumentReference(Train.DOC_ID_PREFIX + train2.get_id(), Train.DOC_TYPE, 1L),
                new DocumentReference(TrainDamageReport.DOC_ID_PREFIX + trainDamageReport.get_id(), TrainDamageReport.DOC_TYPE, 1L),
        },
                integrationAdapter.findByUserAndDevice("user1", "device1").toArray());
    }

    @Test
    public void retrieveDocument() throws NoSuchDataObjectException, IOException {
        when(trainManagerMock.find(train1.get_id())).thenReturn(train1);

        ObjectNode objectNode = integrationAdapter.retrieveDocument(Train.DOC_TYPE, Train.DOC_ID_PREFIX + train1.get_id());
        assertNotNull(objectNode);

        ObjectMapper mapper = new ObjectMapper();
        Train train = mapper.readValue(objectNode, Train.class);

        assertEquals(train1.get_id(), train.get_id());
        assertEquals(train1.getNumber(), train.getNumber());
        assertEquals(train1.get_rev(), train.get_rev());
    }

    private void createTrainDamageReport() {
        trainDamageReport = new TrainDamageReport("10", 1L, "1", "Some damage", "Appear", new Date());

    }

    private void createTrains() {
        train1 = new Train("1", 1L, 200L);
        train2 = new Train("2", 1L, 500L);
    }

    private List<TrainDamageReport> getSomeTrainDamageReports() {
        return Collections.singletonList(trainDamageReport);
    }

    private List<Train> getSomeTrains() {
        List<Train> trains = new ArrayList<>();
        trains.add(train1);
        trains.add(train2);

        return trains;
    }

}