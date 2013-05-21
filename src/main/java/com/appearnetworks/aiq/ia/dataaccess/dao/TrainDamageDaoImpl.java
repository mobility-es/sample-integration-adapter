package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Repository
public class TrainDamageDaoImpl implements TrainDamageDao {

    private final Map<String, TrainDamageReportDO> trainDamageReportMap = new ConcurrentHashMap<String, TrainDamageReportDO>();

    @Autowired
    private TrainDao trainDao;

    @Override
    public void create(TrainDamageReportDO trainDamageReportDO) {


        trainDamageReportMap.put(trainDamageReportDO.getId(), trainDamageReportDO);
    }

    @Override
    public List<TrainDamageReportDO> getAll() {
        return new ArrayList<>(trainDamageReportMap.values());
    }

    @Override
    public TrainDamageReportDO find(String trainDamageReportId) throws NoSuchDataObjectException{
        if(trainDamageReportMap.containsKey(trainDamageReportId)) {
            return trainDamageReportMap.get(trainDamageReportId);
        } else {
            throw new NoSuchDataObjectException("Train damage report with id [" + trainDamageReportId + " ] not found.");
        }
    }
}
