package com.appearnetworks.aiq.ia;

import org.junit.Before;
import org.junit.Test;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapter;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;

import static org.junit.Assert.assertArrayEquals;

public class IntegrationAdapterTest {

    private IntegrationAdapter integrationAdapter;

    @Before
    public void setup() {
        integrationAdapter = new IntegrationAdapterImpl();
    }

    // TODO Test your integration adapter here

    /**
     * TODO Dummy sample, replace me
     */
    @Test
    public void listDocuments() {
        assertArrayEquals(new DocumentReference[] {
                    new DocumentReference("DocId", "DocType", 1L),
                },
                integrationAdapter.findByUserAndDevice("user1", "device1").toArray());
    }

}