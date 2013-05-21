package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.model.TrainTypeDO;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainTypeDaoImpl implements TrainTypeDao {

    private Map<String, TrainTypeDO> trainTypeMap = new ConcurrentHashMap<>();

    @Override
    public void create(TrainTypeDO trainTypeDO) {
        trainTypeMap.put(trainTypeDO.getId(), trainTypeDO);
    }

    @Override
    public TrainTypeDO find(String id) {
        return trainTypeMap.get(id);
    }

    @Override
    public List<TrainTypeDO> getAll() {
        return new ArrayList<>(trainTypeMap.values());
    }
}
