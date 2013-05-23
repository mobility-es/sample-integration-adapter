package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageImageRef;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainDamageReportManager {
    TrainDamageReport create(TrainDamageReport trainDamageReport);
    void createTrainDamageImage(TrainDamageImageRef trainDamageImageRef, String id, String userId, String deviceId);
    long updateTrainDamageImage(String id, String name, String contentType, byte[] data);
    List<TrainDamageImageRef> getTrainDamageImagesByUserIdAndDeviceId(String userId, String deviceId);
    TrainDamageReport find(String trainDamageReportId) throws NoSuchDataObjectException;
    List<TrainDamageReport> getAll();

    TrainDamageImageRef findTrainDamageImageById(String id) throws NoSuchDataObjectException;
}
