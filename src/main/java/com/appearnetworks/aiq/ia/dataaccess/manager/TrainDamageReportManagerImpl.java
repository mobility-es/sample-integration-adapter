package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.*;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageImageRef;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Component
public class TrainDamageReportManagerImpl implements TrainDamageReportManager {

    @Autowired
    private TrainDamageDao trainDamageDao;
    @Autowired
    private TrainDao trainDao;

    @Override
    public void create(TrainDamageReport trainDamageReport) {
        TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();
        TrainDO trainDO = null;

        try {
            trainDO = trainDao.find(trainDamageReport.getTrainId());
        } catch (NoSuchDataObjectException e) {
            throw new IllegalArgumentException("Invalid train damage report.");
        }


        trainDamageReportDO.setDamageCause(trainDamageReport.getDamageCause());

        SelectedDamageCodeDO selectedDamageCodeDO = new SelectedDamageCodeDO(trainDamageReport.getLevel1(),
                trainDamageReport.getLevel2(),
                trainDamageReport.getLevel3(),
                trainDamageReport.getDamageCode());

        trainDamageReportDO.setDamageCode(selectedDamageCodeDO);
        trainDamageReportDO.setDamageDateTime(trainDamageReport.getDamageDateTime());
        trainDamageReportDO.setDamageText(trainDamageReport.getDamageText());
        trainDamageReportDO.setHeading(trainDamageReport.getHeading());
        trainDamageReportDO.setOperation(trainDamageReport.getOperation());
        trainDamageReportDO.setReportedBy(trainDamageReport.getReportedBy());
        trainDamageReportDO.setTrain(trainDO);

        List<TrainPartDO> trainPartDOs = trainDO.getTrainTypeDO().getTrainParts();
        TrainPartDO trainPartDO = findTrainPartDO(trainPartDOs, trainDamageReport);
        trainDamageReportDO.setTrainPart(trainPartDO);

        trainDamageDao.create(trainDamageReportDO);
    }

    @Override
    public void createTrainDamageImage(TrainDamageImageRef trainDamageImageRef, String id, String userId, String deviceId) {
        TrainDamageImageDO trainDamageImageDO = new TrainDamageImageDO();
        trainDamageImageDO.setId(id);
        trainDamageImageDO.setUserId(userId);
        trainDamageImageDO.setDeviceId(deviceId);
        trainDamageImageDO.setDamageId(trainDamageImageRef.getDamageId());
        trainDamageImageDO.setCreationDate(trainDamageImageRef.getCreationDate());

        trainDamageDao.createAttachment(trainDamageImageDO);
    }

    public long updateTrainDamageImage(String id, String name, String contentType, byte[] data) {
        return trainDamageDao.updateAttachment(id, name, contentType, data);
    }

    @Override
    public List<TrainDamageImageRef> getTrainDamageImagesByUserIdAndDeviceId(String userId, String deviceId) {
        return convertToTrainDamageImages(trainDamageDao.getAttachmentsByUserIdAndDeviceId(userId, deviceId));
    }

    private List<TrainDamageImageRef> convertToTrainDamageImages(List<TrainDamageImageDO> trainDamageImageDOs) {
        List<TrainDamageImageRef> trainDamageImageRefs = new ArrayList<>();
        for (TrainDamageImageDO damageImageDO : trainDamageImageDOs) {
            trainDamageImageRefs.add(convertToTrainDamageImage(damageImageDO));
        }

        return trainDamageImageRefs;
    }

    private TrainDamageImageRef convertToTrainDamageImage(TrainDamageImageDO damageImageDO) {
        return new TrainDamageImageRef(damageImageDO.getId(), damageImageDO.getRevision(), damageImageDO.getDamageId(), damageImageDO.getCreationDate());
    }

    @Override
    public TrainDamageReport find(String trainDamageReportId) throws NoSuchDataObjectException {
        return convertToTrainDamageReport(trainDamageDao.find(trainDamageReportId));
    }

    @Override
    public List<TrainDamageReport> getAll() {
        return convertToTrainDamageReports(trainDamageDao.getAll());
    }

    @Override
    public TrainDamageImageRef findTrainDamageImageById(String id) throws NoSuchDataObjectException {
        return convertToTrainDamageImage(trainDamageDao.findAttachmentById(id));
    }

    private TrainDamageReport convertToTrainDamageReport(TrainDamageReportDO trainDamageReportDO) {
        return new TrainDamageReport(trainDamageReportDO.getId(),
                trainDamageReportDO.getRev(),
                trainDamageReportDO.getTrain().getId(),
                trainDamageReportDO.getDamageCode().getLevel1(),
                trainDamageReportDO.getDamageCode().getLevel2(),
                trainDamageReportDO.getDamageCode().getLevel3(),
                trainDamageReportDO.getTrainPart().getName(),
                trainDamageReportDO.getDamageCode().getName(),
                trainDamageReportDO.getHeading(),
                trainDamageReportDO.getDamageText(),
                trainDamageReportDO.getReportedBy(),
                trainDamageReportDO.getDamageDateTime(),
                trainDamageReportDO.getDamageCause(),
                trainDamageReportDO.getOperation());
    }

    private List<TrainDamageReport> convertToTrainDamageReports(List<TrainDamageReportDO> trainDamageReportDOs) {

        List<TrainDamageReport> trainDamageReports = new ArrayList<>();
        for(TrainDamageReportDO trainDamageReportDO : trainDamageReportDOs) {
            trainDamageReports.add(convertToTrainDamageReport(trainDamageReportDO));
        }
        return trainDamageReports;
    }

    private TrainPartDO findTrainPartDO(List<TrainPartDO> trainPartDOs, TrainDamageReport trainDamageReport) {
        for (TrainPartDO trainPartDO : trainPartDOs) {
            if(trainPartDO.getName().equals(trainDamageReport.getTrainPart())) {
                return trainPartDO;
            }
        }

        throw new IllegalArgumentException("No such train part for train with id [" + trainDamageReport.getTrainId() + "]");
    }
}
