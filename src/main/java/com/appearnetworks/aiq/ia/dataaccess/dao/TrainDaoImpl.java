package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainTypeDO;
import com.appearnetworks.aiq.ia.rest.model.Train;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Repository
public class TrainDaoImpl implements TrainDao {

    private static final Map<String, TrainDO> trainMap = new ConcurrentHashMap<String, TrainDO>();

    @Override
    public void create(Train train) {
        String uuid = generateUUID();
        Long number = train.getNumber();
        TrainTypeDO trainTypeDO = new TrainTypeDO(train.getTrainType());
        TrainDO trainDO = new TrainDO(uuid, number, trainTypeDO);

        trainMap.put(uuid, trainDO);
    }

    @Override
    public List<Train> getAll() {
        return convertToTrains(trainMap.values());
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private List<Train> convertToTrains(Collection<TrainDO> trainDOs) {
        List<Train> trains = new ArrayList<Train>();

        for(TrainDO trainDO : trainDOs) {
            trains.add(convertToTrain(trainDO));
        }

        return trains;
    }

    private Train convertToTrain(TrainDO trainDO) {
        String id = trainDO.getId();
        Long number = trainDO.getNumber();
        String type = trainDO.getTrainTypeDO().getType();
        return new Train(id, number, type);
    }
}
