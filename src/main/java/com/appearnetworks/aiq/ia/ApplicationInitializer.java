package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.DamageCodeDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainPartDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainTypeDO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


    @Override
    public void afterPropertiesSet() throws Exception {

        DamageCodeDO damageCodeDO1 = new DamageCodeDO("l11", "l12", "l13", "1");
        DamageCodeDO damageCodeDO2 = new DamageCodeDO("l21", "l22", "l23", "2");
        DamageCodeDO damageCodeDO3 = new DamageCodeDO("l31", "l32", "l33", "1");
        DamageCodeDO damageCodeDO4 = new DamageCodeDO("l41", "l42", "l23", "2");

        TrainPartDO trainPartDO1 = new TrainPartDO("tp1", "tpv1");
        TrainPartDO trainPartDO2 = new TrainPartDO("tp2", "tpv2");
        TrainPartDO trainPartDO3 = new TrainPartDO("tp3", "tpv3");
        TrainPartDO trainPartDO4 = new TrainPartDO("tp4", "tpv4");

        //Create train types
        TrainTypeDO trainTypeDO1 = new TrainTypeDO();
        trainTypeDO1.setName("trainType1");

        List<DamageCodeDO> trainTypeDO1damageCodes = new ArrayList<>();
        trainTypeDO1damageCodes.add(damageCodeDO1);
        trainTypeDO1damageCodes.add(damageCodeDO2);
        trainTypeDO1.setDamageCodes(trainTypeDO1damageCodes);

        List<TrainPartDO> trainTypeDO1trainParts = new ArrayList<>();
        trainTypeDO1trainParts.add(trainPartDO1);
        trainTypeDO1trainParts.add(trainPartDO2);
        trainTypeDO1.setTrainParts(trainTypeDO1trainParts);

        TrainTypeDO trainTypeDO2 = new TrainTypeDO();
        trainTypeDO2.setName("trainType2");
        
        List<DamageCodeDO> trainTypeDO2damageCodes = new ArrayList<>();
        trainTypeDO2damageCodes.add(damageCodeDO3);
        trainTypeDO1damageCodes.add(damageCodeDO4);
        trainTypeDO2.setDamageCodes(trainTypeDO2damageCodes);

        List<TrainPartDO> trainTypeDO2trainParts = new ArrayList<>();
        trainTypeDO2trainParts.add(trainPartDO3);
        trainTypeDO2trainParts.add(trainPartDO4);
        trainTypeDO2.setTrainParts(trainTypeDO2trainParts);

        //Create trains
        trainDao.create(new TrainDO(1L, trainTypeDO1));
        trainDao.create(new TrainDO(2L, trainTypeDO1));
        trainDao.create(new TrainDO(3L, trainTypeDO1));
        trainDao.create(new TrainDO(4L, trainTypeDO1));
        trainDao.create(new TrainDO(5L, trainTypeDO1));

        trainDao.create(new TrainDO(6L, trainTypeDO2));
        trainDao.create(new TrainDO(7L, trainTypeDO2));
        trainDao.create(new TrainDO(8L, trainTypeDO2));
        trainDao.create(new TrainDO(9L, trainTypeDO2));
        trainDao.create(new TrainDO(10L, trainTypeDO2));


    }
}
