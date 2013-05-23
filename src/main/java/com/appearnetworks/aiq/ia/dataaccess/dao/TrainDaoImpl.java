package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of <code>TrainDao</code> which stores train damage reports in memory.
 */
@Repository
public class TrainDaoImpl implements TrainDao {

    private final Map<String, TrainDO> trainMap = new ConcurrentHashMap<String, TrainDO>();

    @Override
    public void create(TrainDO trainDO) {
        trainMap.put(trainDO.getId(), trainDO);
    }

    @Override
    public TrainDO find(String id) throws NoSuchDataObjectException {
        if(trainMap.containsKey(id)) {
            return trainMap.get(id);
        } else {
            throw new NoSuchDataObjectException();
        }
    }

    @Override
    public List<TrainDO> getAll() {
        return new ArrayList<>(trainMap.values());
    }
}
