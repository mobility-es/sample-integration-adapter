package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageReportDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * This class initializes the application with initial data.
 */
public class ApplicationInitializer implements InitializingBean {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainDamageReportDao trainDamageDao;


    @Override
    public void afterPropertiesSet() throws Exception {
        createTrains();
        createTrainDamages();
    }

    private void createTrainDamages() {
        List<TrainDO> trainDOs = trainDao.getAll();

        for (TrainDO trainDO : trainDOs) {
            TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();

            trainDamageReportDO.setCreationDateTime(new Date());
            trainDamageReportDO.setDescription("Should be fixed");
            trainDamageReportDO.setReportedBy("Alex");
            trainDamageReportDO.setTrain(trainDO);

            trainDamageDao.create(trainDamageReportDO);
        }
    }

    private void createTrains() {

        //Create trains
        trainDao.create(new TrainDO(1L));
        trainDao.create(new TrainDO(2L));
        trainDao.create(new TrainDO(3L));
        trainDao.create(new TrainDO(4L));
        trainDao.create(new TrainDO(5L));
        trainDao.create(new TrainDO(6L));
        trainDao.create(new TrainDO(7L));
        trainDao.create(new TrainDO(8L));
        trainDao.create(new TrainDO(9L));
        trainDao.create(new TrainDO(10L));
    }


}
