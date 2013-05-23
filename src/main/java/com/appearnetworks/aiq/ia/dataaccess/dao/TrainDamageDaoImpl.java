package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageImageDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
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
    public TrainDamageReportDO create(TrainDamageReportDO trainDamageReportDO) {
        trainDamageReportMap.put(trainDamageReportDO.getId(), trainDamageReportDO);
        return trainDamageReportDO;
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
    public List<TrainDamageImageDO> getAttachmentsByUserIdAndDeviceId(String userId, String deviceId) {
        List<TrainDamageImageDO> trainDamageImageDOs = new ArrayList<>();

        for (TrainDamageImageDO trainDamageImageDO : trainDamageImageMap.values()) {
            if(trainDamageImageDO.getUserId().equals(userId) && trainDamageImageDO.getDeviceId().equals(deviceId)){
                trainDamageImageDOs.add(trainDamageImageDO);
            }
        }

        return trainDamageImageDOs;
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

    @Override
    public TrainDamageImageDO findAttachmentById(String id) throws NoSuchDataObjectException {
        if(trainDamageImageMap.containsKey(id)){
            return trainDamageImageMap.get(id);
        }else {
            throw new NoSuchDataObjectException("Train damage image with id [" + id + " ] not found.");
        }
    }
}
