package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageReportDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.*;
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
    private TrainDamageReportDao trainDamageDao;
    @Autowired
    private TrainDao trainDao;

    @Override
    public TrainDamageReport create(TrainDamageReport trainDamageReport) {
        TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();
        TrainDO trainDO = null;

        try {
            trainDO = trainDao.find(trainDamageReport.getTrainId());
        } catch (NoSuchDataObjectException e) {
            throw new IllegalArgumentException("Invalid train damage report.");
        }

        trainDamageReportDO.setCreationDateTime(trainDamageReport.getCreationDateTime());
        trainDamageReportDO.setDescription(trainDamageReport.getDescription());
        trainDamageReportDO.setReportedBy(trainDamageReport.getReportedBy());
        trainDamageReportDO.setTrain(trainDO);




        return convertToTrainDamageReport(trainDamageDao.create(trainDamageReportDO));
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
                trainDamageReportDO.getDescription(),
                trainDamageReportDO.getReportedBy(),
                trainDamageReportDO.getCreationDateTime());
    }

    private List<TrainDamageReport> convertToTrainDamageReports(List<TrainDamageReportDO> trainDamageReportDOs) {

        List<TrainDamageReport> trainDamageReports = new ArrayList<>();
        for(TrainDamageReportDO trainDamageReportDO : trainDamageReportDOs) {
            trainDamageReports.add(convertToTrainDamageReport(trainDamageReportDO));
        }
        return trainDamageReports;
    }

}
