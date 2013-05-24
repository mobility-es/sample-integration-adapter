package com.appearnetworks.aiq.ia.manager;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageReportDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.manager.exception.TrainDamageReportNotFoundException;
import com.appearnetworks.aiq.ia.dataaccess.model.*;
import com.appearnetworks.aiq.ia.manager.exception.TrainNotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of <code>TrainDamageReportManager</code>
 *
 */
@Component
public class TrainDamageReportManagerImpl implements TrainDamageReportManager {

    @Autowired
    private TrainDamageReportDao trainDamageReportDao;

    @Autowired
    private TrainDao trainDao;

    @Override
    public TrainDamageReport create(TrainDamageReport trainDamageReport) {
        TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();
        TrainDO trainDO = null;

        try {
            trainDO = trainDao.find(trainDamageReport.getTrainId());
        } catch (NoSuchDataObjectException e) {
            throw new IllegalArgumentException(
                    new TrainNotFoundException(
                            "No train found for id [" + trainDamageReport.getTrainId() + "] in the train damage report."));
        }

        trainDamageReportDO.setCreationDateTime(trainDamageReport.getCreationDateTime());
        trainDamageReportDO.setDescription(trainDamageReport.getDescription());
        trainDamageReportDO.setReportedBy(trainDamageReport.getReportedBy());
        trainDamageReportDO.setTrain(trainDO);

        return convertToTrainDamageReport(trainDamageReportDao.create(trainDamageReportDO));
    }

    @Override
    public TrainDamageReport find(String id) throws TrainDamageReportNotFoundException {
        try {
            TrainDamageReportDO trainDamageReportDO = trainDamageReportDao.find(id);
            return convertToTrainDamageReport(trainDamageReportDO);
        } catch (NoSuchDataObjectException e) {
            throw new TrainDamageReportNotFoundException("Train damage report not found for id [" + id + "]");
        }

    }

    @Override
    public List<TrainDamageReport> getAll() {
        return convertToTrainDamageReports(trainDamageReportDao.getAll());
    }

    private TrainDamageReport convertToTrainDamageReport(TrainDamageReportDO trainDamageReportDO) {
        return new TrainDamageReport(TrainDamageReport.DOC_ID_PREFIX + trainDamageReportDO.getId(),
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
