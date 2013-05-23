package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        createTrains();
        createTrainDamages();
    }

    private void createTrainDamages() {
        List<TrainDO> trainDOs = trainDao.getAll();

        for (TrainDO trainDO : trainDOs) {
            TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();
            trainDamageReportDO.setDamageCause("Out of order");

            DamageCodeDO damageCodeDO = trainDO.getTrainTypeDO().getDamageCodes().get(0);

            String level1 = damageCodeDO.getName();
            SubCode1DO subCode1DO = damageCodeDO.getSubCode1DOs().get(0);
            String level2 = subCode1DO.getName();
            String level3 = subCode1DO.getSubCode2DOs().get(0).getName();
            String name = subCode1DO.getSubCode2DOs().get(0).getId();

            SelectedDamageCodeDO selectedDamageCodeDO = new SelectedDamageCodeDO(level1, level2, level3, name);

            trainDamageReportDO.setDamageCode(selectedDamageCodeDO);
            trainDamageReportDO.setDamageDateTime(new Date());
            trainDamageReportDO.setDamageText("Should be fixed");
            trainDamageReportDO.setHeading("Important to fix");
            trainDamageReportDO.setOperation("Night operation");
            trainDamageReportDO.setReportedBy("Alex");
            trainDamageReportDO.setTrain(trainDO);
            trainDamageReportDO.setTrainPart(trainDO.getTrainTypeDO().getTrainParts().get(0));

            trainDamageDao.create(trainDamageReportDO);
        }
    }

    private void createTrains() {
        SubCode2DO subCode2DO1 = new SubCode2DO("1", "sc21");
        SubCode2DO subCode2DO2 = new SubCode2DO("1", "sc22");
        List<SubCode2DO> sc11SubCode2DOs = new ArrayList<>();
        sc11SubCode2DOs.add(subCode2DO1);
        sc11SubCode2DOs.add(subCode2DO2);
        SubCode1DO subCode1DO1 = new SubCode1DO("sc11", sc11SubCode2DOs);

        SubCode2DO subCode2DO3 = new SubCode2DO("1", "sc23");
        SubCode2DO subCode2DO4 = new SubCode2DO("1", "sc24");
        List<SubCode2DO> sc12SubCode2DOs = new ArrayList<>();
        sc12SubCode2DOs.add(subCode2DO3);
        sc11SubCode2DOs.add(subCode2DO4);
        SubCode1DO subCode1DO2 = new SubCode1DO("sc12", sc12SubCode2DOs);

        List<SubCode1DO> dc1SubCode1DOs = new ArrayList<>();
        dc1SubCode1DOs.add(subCode1DO1);
        dc1SubCode1DOs.add(subCode1DO2);
        DamageCodeDO damageCodeDO1 = new DamageCodeDO("dc1", dc1SubCode1DOs);




        SubCode2DO subCode2DO5 = new SubCode2DO("2", "sc25");
        List<SubCode2DO> sc13SubCode2DOs = Collections.singletonList(subCode2DO5);
        SubCode1DO subCode1DO3 = new SubCode1DO("sc13", sc13SubCode2DOs);

        SubCode2DO subCode2DO6 = new SubCode2DO("2", "sc26");
        List<SubCode2DO> sc14SubCode2DOs = Collections.singletonList(subCode2DO6);
        SubCode1DO subCode1DO4 = new SubCode1DO("sc14", sc13SubCode2DOs);

        List<SubCode1DO> dc2SubCode1DOs = new ArrayList<>();
        dc2SubCode1DOs.add(subCode1DO3);
        dc2SubCode1DOs.add(subCode1DO4);
        DamageCodeDO damageCodeDO2 = new DamageCodeDO("dc2", dc2SubCode1DOs);

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
        trainTypeDO2damageCodes.add(damageCodeDO1);
        trainTypeDO1damageCodes.add(damageCodeDO2);
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
