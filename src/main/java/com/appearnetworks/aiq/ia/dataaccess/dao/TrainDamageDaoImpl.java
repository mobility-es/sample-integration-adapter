package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import com.appearnetworks.aiq.ia.rest.model.TrainDamageReport;
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
    public void create(TrainDamageReport trainDamageReport) {
        TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();
        trainDamageReportDO.setDamageCause(trainDamageReport.getDamageCause());

        trainDamageReportDO.setDamageCode(trainDamageReport.getDamageCode());
        trainDamageReportDO.setDamageDateTime(trainDamageReport.getDamageDateTime());
        trainDamageReportDO.setDamageText(trainDamageReport.getDamageText());
        trainDamageReportDO.setHeading(trainDamageReport.getHeading());
        trainDamageReportDO.setLevel1(trainDamageReport.getLevel1());
        trainDamageReportDO.setLevel2(trainDamageReport.getLevel2());
        trainDamageReportDO.setLevel3(trainDamageReport.getLevel3());
        trainDamageReportDO.setOperation(trainDamageReport.getOperation());
        trainDamageReportDO.setReportedBy(trainDamageReport.getReportedBy());

        TrainDO trainDO = null;

        try {
            trainDO = trainDao.find(trainDamageReport.getTrainId());
        } catch (NoSuchDataObjectException e) {
            throw new IllegalArgumentException("Invalid train damage report.");
        }

        trainDamageReportDO.setTrain(trainDO);
        trainDamageReportDO.setTrainPart(trainDamageReport.getTrainPart());

        trainDamageReportMap.put(trainDamageReportDO.getId(), trainDamageReportDO);
    }

    @Override
    public List<TrainDamageReportDO> getAll() {
        return new ArrayList<>(trainDamageReportMap.values());
    }
}
