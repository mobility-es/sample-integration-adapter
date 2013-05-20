package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainTypeDO;
import com.appearnetworks.aiq.ia.rest.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Repository
public class TrainDaoImpl implements TrainDao {

    private final Map<String, TrainDO> trainMap = new ConcurrentHashMap<String, TrainDO>();

    @Override
    public void create(Train train) {

        Long number = train.getNumber();
        TrainTypeDO trainTypeDO = new TrainTypeDO(train.getTrainType());
        TrainDO trainDO = new TrainDO(number, trainTypeDO);

        trainMap.put(trainDO.getId(), trainDO);
    }

    @Override
    public TrainDO find(String trainId) throws NoSuchDataObjectException {
        if(trainMap.containsKey(trainId)) {
            return trainMap.get(trainId);
        } else {
            throw new NoSuchDataObjectException("Train with id [" + trainId + " ] not found.");
        }
    }

    @Override
    public List<TrainDO> getAll() {
        return new ArrayList<>(trainMap.values());
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
        Long rev = trainDO.getRev();
        Long number = trainDO.getNumber();
        String type = trainDO.getTrainTypeDO().getType();

        Train train = new Train();
        train.setId(id);
        train.setRev(rev);
        train.setTrainType(type);
        train.setNumber(number);

        return train;
    }
}
