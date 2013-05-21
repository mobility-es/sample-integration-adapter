package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.model.TrainTypeDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainTypeDao {
    void create(TrainTypeDO trainTypeDO);
    TrainTypeDO find(String id);
    List<TrainTypeDO> getAll();
}
