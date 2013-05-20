package com.appearnetworks.aiq.ia;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import com.appearnetworks.aiq.integrationframework.integration.IntegrationAdapterBase;
import com.appearnetworks.aiq.integrationframework.integration.DocumentReference;

@Component
public class IntegrationAdapterImpl extends IntegrationAdapterBase {

    // TODO Implement your integration adapter here

    /**
     * TODO Dummy sample, replace me
     */
    @Override
    public List<DocumentReference> findByUserAndDevice(String userId, String deviceId) {
        List<DocumentReference> documentReferences = new ArrayList<>();
        documentReferences.add(new DocumentReference("DocId", "DocType", 1L));
        return documentReferences;
    }

}
