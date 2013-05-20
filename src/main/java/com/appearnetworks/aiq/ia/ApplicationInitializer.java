package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.rest.model.Train;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Component
public class ApplicationInitializer implements InitializingBean {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainDamageDao trainDamageDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        trainDao.create(new Train("1", 1L, "train1"));
        trainDao.create(new Train("2", 2L, "train2"));
        trainDao.create(new Train("3", 3L, "train3"));
        trainDao.create(new Train("4", 4L, "train4"));
        trainDao.create(new Train("5", 5L, "train5"));
        trainDao.create(new Train("6", 6L, "train6"));
        trainDao.create(new Train("7", 7L, "train7"));
        trainDao.create(new Train("8", 8L, "train8"));
        trainDao.create(new Train("9", 9L, "train9"));
        trainDao.create(new Train("10", 10L, "train10"));
    }
}
