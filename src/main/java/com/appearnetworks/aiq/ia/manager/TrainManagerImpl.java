package com.appearnetworks.aiq.ia.manager;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.manager.exception.NotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class TrainManagerImpl implements TrainManager {

    @Autowired
    private TrainDao trainDao;

    @Override
    public Train find(String id) throws NotFoundException {
        try {
            TrainDO trainDO = trainDao.find(id);
            return convertToTrain(trainDO);
        } catch (NoSuchDataObjectException e) {
            throw new NotFoundException("No train with id [" + id + "] exists.");
        }
    }

    @Override
    public List<Train> getAll() {
        return convertToTrains(trainDao.getAll());
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
        long rev = trainDO.getRev();
        long number = trainDO.getNumber();
        return new Train(id, rev, number);
    }

}
