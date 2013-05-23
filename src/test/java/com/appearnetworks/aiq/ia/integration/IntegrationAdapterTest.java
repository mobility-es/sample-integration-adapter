package com.appearnetworks.aiq.ia.integration;

import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapter;
import com.appearnetworks.aiq.integrationframework.integration.UpdateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/testApplicationContext.xml" })
public class IntegrationAdapterTest {

    @Autowired
    IntegrationAdapter integrationAdapter;


    @Test
    public void addAndGetTrainDamageReport() throws UpdateException {
    }

}
