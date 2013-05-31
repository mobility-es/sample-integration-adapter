package com.appearnetworks.aiq.ia;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDamageReportDao;
import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


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
        List<String> trainDamageDescriptions = getTrainDamageDescriptions();

        //pick train damage descriptions randomly for each created train damage report from the list of 20 damage descriptions
        Random random = new Random();
        int min = 0;
        int max = 19;

        for (TrainDO trainDO : trainDOs) {
            // create two damage reports per train
            int range = max - min + 1;
            int index = random.nextInt(range) + min;

            String description = trainDamageDescriptions.get(index);
            trainDamageDao.create(createTrainDamageDo(trainDO, description));

            boolean unique = false;
            int secondIndex = 0;
            while(!unique) {
                secondIndex = random.nextInt(range) + min;
                if(secondIndex != index) {
                    unique = true;
                }
            }
            description = trainDamageDescriptions.get(secondIndex);
            trainDamageDao.create(createTrainDamageDo(trainDO, description));
        }
    }

    private void createTrains() {
        //Create trains
        trainDao.create(new TrainDO(9001L));
        trainDao.create(new TrainDO(9002L));
        trainDao.create(new TrainDO(9003L));
        trainDao.create(new TrainDO(9004L));
        trainDao.create(new TrainDO(9005L));
        trainDao.create(new TrainDO(9006L));
        trainDao.create(new TrainDO(9007L));
    }

    private TrainDamageReportDO createTrainDamageDo(TrainDO trainDO, String description){
        TrainDamageReportDO trainDamageReportDO = new TrainDamageReportDO();

        trainDamageReportDO.setCreationDateTime(new Date());
        trainDamageReportDO.setDescription(description);
        trainDamageReportDO.setReportedBy("Alex Schulman");
        trainDamageReportDO.setTrain(trainDO);

        return trainDamageReportDO;
    }

    private List<String> getTrainDamageDescriptions(){
        String[] damageDescriptions = {
                "Paper towels are missing in cabinet",
                "A3000 OMR faulty. A3004 faulty. Sometimes A3040 OMR faulty in section B, losing charge",
                "30C + in cabin, sometimes cold. No error reported",
                "Compressed air system shows high oil temp. Left it to cool down. Hopefully fine now",
                "Vehicle information systems displays error B8480",
                "Door number 4 is malfunctioning. Out of order",
                "Poor ventilation in passenger cabin. No cold air available",
                "Headlight beam, left hand side, not functioning",
                "Monitor B, poor picture quality",
                "The vehicle needs exterior cleaning. I cannot see anything through rear view mirrors/ cameras",
                "Toilet malfunction. Cannot flush.",
                "Brake system computer communications error. Codes 8472, 8476",
                "DMB vehicle traction engine fan 2: Sending code: C 4076",
                "Toilet excrement tank more than 50% full!",
                "Graffiti on car exterior, section DMA, DMB",
                "Passenger cabin window does not open. Handle is broken",
                "Water closet sink is blocked",
                "B8411 primary brake computer shows comm error",
                "Out of washer fluid for windscreen wipers",
                "Information system screens malfunction. Blank screens",
                "First aid kit is not sealed"
        };

        return Arrays.asList(damageDescriptions);
    }


}
