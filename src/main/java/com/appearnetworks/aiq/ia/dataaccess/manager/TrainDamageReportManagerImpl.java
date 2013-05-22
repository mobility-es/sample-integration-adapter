package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageImageDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageImage;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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
        trainDamageDao.create(trainDamageReportDO);
    }

    @Override
    public void createTrainDamageImage(TrainDamageImage trainDamageImage, String id, String userId, String deviceId) {
        TrainDamageImageDO trainDamageImageDO = new TrainDamageImageDO();
        trainDamageImageDO.setId(id);
        trainDamageImageDO.setUserId(userId);
        trainDamageImageDO.setDeviceId(deviceId);
        trainDamageImageDO.setDamageId(trainDamageImage.getDamageId());
        trainDamageImageDO.setCreationDate(new Date(trainDamageImage.getCreationDate()));
        trainDamageDao.createAttachment(trainDamageImageDO);
    }

    public long updateTrainDamageImage(String id, String name, String contentType, byte[] data) {
        return trainDamageDao.updateAttachment(id, name, contentType, data);
    }

    @Override
    public TrainDamageReport find(String trainDamageReportId) throws NoSuchDataObjectException {
        return convertToTrainDamageReport(trainDamageDao.find(trainDamageReportId));
    }

    @Override
    public List<TrainDamageReport> getAll() {
        return convertToTrainDamageReports(trainDamageDao.getAll());
    }

    private TrainDamageReport convertToTrainDamageReport(TrainDamageReportDO trainDamageReportDO) {
        return new TrainDamageReport(trainDamageReportDO.getId(),
                trainDamageReportDO.getRev(),
                trainDamageReportDO.getTrain().getId(),
                trainDamageReportDO.getLevel1(),
                trainDamageReportDO.getLevel2(),
                trainDamageReportDO.getLevel3(),
                trainDamageReportDO.getTrainPart(),
                trainDamageReportDO.getDamageCode(),
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
}
