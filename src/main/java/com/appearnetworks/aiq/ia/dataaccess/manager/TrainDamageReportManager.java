package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageImage;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainDamageReportManager {
    void create(TrainDamageReport trainDamageReport);
    void createTrainDamageImage(TrainDamageImage trainDamageImage, String id, String userId, String deviceId);
    long updateTrainDamageImage(String id, String name, String contentType, byte[] data);
    TrainDamageReport find(String trainDamageReportId) throws NoSuchDataObjectException;
    List<TrainDamageReport> getAll();
}
