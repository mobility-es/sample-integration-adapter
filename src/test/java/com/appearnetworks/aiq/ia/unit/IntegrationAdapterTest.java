package com.appearnetworks.aiq.ia.unit;

import com.appearnetworks.aiq.ia.IntegrationAdapterImpl;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.manager.TrainDamageReportManager;
import com.appearnetworks.aiq.ia.manager.TrainManager;
import com.appearnetworks.aiq.ia.manager.exception.TrainNotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapter;
import com.appearnetworks.aiq.integrationframework.integration.UpdateException;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void retrieveDocument() throws TrainNotFoundException, IOException {
        when(trainManagerMock.find(train1.get_id())).thenReturn(train1);

        ObjectNode objectNode = integrationAdapter.retrieveDocument(Train.DOC_TYPE, Train.DOC_ID_PREFIX + train1.get_id());
        assertNotNull(objectNode);

        ObjectMapper mapper = new ObjectMapper();
        Train train = mapper.readValue(objectNode, Train.class);

        assertEquals(train1.get_id(), train.get_id());
        assertEquals(train1.getNumber(), train.getNumber());
        assertEquals(train1.get_rev(), train.get_rev());
    }

    @Test
    public void insertDocument() throws UpdateException {

        when(trainDamageReportManagerMock.create(any(TrainDamageReport.class))).thenReturn(trainDamageReport);

        long rev = integrationAdapter.insertDocument("user1",
                "device1",
                new DocumentReference("1", TrainDamageReport.DOC_TYPE, 1L),
                createTrainDamageReportDocument());

        verify(trainDamageReportManagerMock, times(1)).create(any(TrainDamageReport.class));
        assertEquals(trainDamageReport.get_rev(), rev);
    }

    private ObjectNode createTrainDamageReportDocument() {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.valueToTree(trainDamageReport);
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