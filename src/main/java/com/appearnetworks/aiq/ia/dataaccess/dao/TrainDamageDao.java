package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageImageDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainDamageDao {

    void create(TrainDamageReportDO trainDamageReportDO);
    void createAttachment(TrainDamageImageDO trainDamageImageDO);
    long updateAttachment(String id, String name, String contentType, byte[] data);
    List<TrainDamageReportDO> getAll();
    TrainDamageReportDO find(String trainDamageReportId) throws NoSuchDataObjectException;
}
