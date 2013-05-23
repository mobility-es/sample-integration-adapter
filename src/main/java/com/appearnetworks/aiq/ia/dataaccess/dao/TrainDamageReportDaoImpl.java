package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of <code>TrainDamageReportDao</code> which stores train damage reports in memory.
 *
 */
@Repository
public class TrainDamageReportDaoImpl implements TrainDamageReportDao {

    private final Map<String, TrainDamageReportDO> trainDamageReportMap = new ConcurrentHashMap<String, TrainDamageReportDO>();

    @Override
    public TrainDamageReportDO create(TrainDamageReportDO trainDamageReportDO) {
        //TODO throw error if train already exists.
        trainDamageReportMap.put(trainDamageReportDO.getId(), trainDamageReportDO);
        return trainDamageReportDO;
    }

    @Override
    public List<TrainDamageReportDO> getAll() {
        return new ArrayList<>(trainDamageReportMap.values());
    }

    @Override
    public TrainDamageReportDO find(String id) throws NoSuchDataObjectException{
        if(trainDamageReportMap.containsKey(id)) {
            return trainDamageReportMap.get(id);
        } else {
            throw new NoSuchDataObjectException();
        }
    }
}
