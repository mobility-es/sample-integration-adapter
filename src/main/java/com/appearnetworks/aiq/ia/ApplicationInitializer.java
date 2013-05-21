package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.DamageCodeDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainTypeDao;
import com.appearnetworks.aiq.ia.dataaccess.manager.TrainManager;
import com.appearnetworks.aiq.ia.dataaccess.model.DamageCodeDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainTypeDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import com.appearnetworks.aiq.ia.model.mobile.TrainType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Component
public class ApplicationInitializer implements InitializingBean {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainDamageDao trainDamageDao;

    @Autowired
    private TrainTypeDao trainTypeDao;

    @Autowired
    private DamageCodeDao damageCodeDao;


    @Override
    public void afterPropertiesSet() throws Exception {

        damageCodeDao.create(new DamageCodeDO("l11", "l12", "l13", "1"));
        damageCodeDao.create(new DamageCodeDO("l21", "l22", "l23", "2"));
        damageCodeDao.create(new DamageCodeDO("l31", "l32", "l33", "1"));
        damageCodeDao.create(new DamageCodeDO("l41", "l42", "l23", "2"));

        List<DamageCodeDO> damageCodeDOs = damageCodeDao.getAll();

        //Create train types
        TrainTypeDO trainTypeDO1 = new TrainTypeDO("trainType1");
        trainTypeDao.create(new TrainTypeDO("trainType1"));

        TrainTypeDO trainTypeDO2 = new TrainTypeDO("trainType2");
        trainTypeDao.create(new TrainTypeDO("trainType2"));

        List<TrainTypeDO> trainTypeDOs = trainTypeDao.getAll();

        //Create trains
        TrainTypeDO fromTrainTypeDO1 = trainTypeDOs.get(0);
        trainDao.create(new TrainDO(1L, trainTypeDO1));
        trainDao.create(new TrainDO(2L, trainTypeDO1));
        trainDao.create(new TrainDO(3L, trainTypeDO1));
        trainDao.create(new TrainDO(4L, trainTypeDO1));
        trainDao.create(new TrainDO(5L, trainTypeDO1));

        TrainTypeDO fromTrainTypeDO2 = trainTypeDOs.get(1);
        trainDao.create(new TrainDO(6L, trainTypeDO2));
        trainDao.create(new TrainDO(7L, trainTypeDO2));
        trainDao.create(new TrainDO(8L, trainTypeDO2));
        trainDao.create(new TrainDO(9L, trainTypeDO2));
        trainDao.create(new TrainDO(10L, trainTypeDO2));


        
    }
}
