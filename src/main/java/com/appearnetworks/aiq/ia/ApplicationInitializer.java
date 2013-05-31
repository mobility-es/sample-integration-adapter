package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageReportDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


/**
 * This class initializes the application with initial data.
 */
public class ApplicationInitializer implements InitializingBean {
    public static final Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainDamageReportDao trainDamageDao;


    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("Initializing application with train and train damage reports...");

        createTrains();
        createTrainDamages();
    }

    private void createTrainDamages() {
        List<TrainDO> trainDOs = trainDao.getAll();

        for (TrainDO trainDO : trainDOs) {
            TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();

            trainDamageReportDO.setCreationDateTime(new Date());
            trainDamageReportDO.setDescription("Part number 9507 maintenance.");
            trainDamageReportDO.setReportedBy("Alex Schulman");
            trainDamageReportDO.setTrain(trainDO);

            trainDamageDao.create(trainDamageReportDO);
        }
    }

    private void createTrains() {
        //Create trains
        trainDao.create(new TrainDO(9001L));
        trainDao.create(new TrainDO(9002L));
        trainDao.create(new TrainDO(9003L));
        trainDao.create(new TrainDO(9005L));
        trainDao.create(new TrainDO(9006L));
        trainDao.create(new TrainDO(9007L));
        trainDao.create(new TrainDO(9008L));
        trainDao.create(new TrainDO(9010L));
        trainDao.create(new TrainDO(9011L));
        trainDao.create(new TrainDO(9012L));
    }


}
