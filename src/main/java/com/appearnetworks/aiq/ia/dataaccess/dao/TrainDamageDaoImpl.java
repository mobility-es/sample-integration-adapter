package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageImageDO;
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
    private final Map<String, TrainDamageImageDO> trainDamageImageMap = new ConcurrentHashMap<>();

    @Autowired
    private TrainDao trainDao;

    @Override
    public void create(TrainDamageReportDO trainDamageReportDO) {
        trainDamageReportMap.put(trainDamageReportDO.getId(), trainDamageReportDO);
    }

    @Override
    public long updateAttachment(String id, String name, String contentType, byte[] data) {
        TrainDamageImageDO trainDamageImageDO = trainDamageImageMap.get(id);
        long newRevision = trainDamageImageDO.getRevision() + 1;
        trainDamageImageDO.setName(name);
        trainDamageImageDO.setContentType(contentType);
        trainDamageImageDO.setAttachment(data);
        trainDamageImageDO.setRevision(newRevision);
        trainDamageImageMap.put(id, trainDamageImageDO);
        return newRevision;
    }

    @Override
    public void createAttachment(TrainDamageImageDO trainDamageImageDO) {
        trainDamageImageMap.put(trainDamageImageDO.getId(), trainDamageImageDO);
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
