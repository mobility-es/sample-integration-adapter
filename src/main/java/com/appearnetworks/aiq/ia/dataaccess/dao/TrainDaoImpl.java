package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Repository
public class TrainDaoImpl implements TrainDao {

    private final Map<String, TrainDO> trainMap = new ConcurrentHashMap<String, TrainDO>();

    @Override
    public void create(TrainDO trainDO) {
        trainMap.put(trainDO.getId(), trainDO);
    }

    @Override
    public TrainDO find(String trainId) throws NoSuchDataObjectException {
        if(trainMap.containsKey(trainId)) {
            return trainMap.get(trainId);
        } else {
            throw new NoSuchDataObjectException("Train with id [" + trainId + " ] not found.");
        }
    }

    @Override
    public List<TrainDO> getAll() {
        return new ArrayList<>(trainMap.values());
    }
}
