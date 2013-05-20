package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import com.appearnetworks.aiq.ia.rest.model.TrainDamageReport;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainDamageDao {

    void create(TrainDamageReport trainDamageReport);
    List<TrainDamageReportDO> getAll();
}
