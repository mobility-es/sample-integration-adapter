package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;

import java.util.List;

/**
 *
 */
public interface TrainDamageReportDao {
    TrainDamageReportDO create(TrainDamageReportDO trainDamageReportDO);
    List<TrainDamageReportDO> getAll();
    TrainDamageReportDO find(String trainDamageReportId) throws NoSuchDataObjectException;
}
