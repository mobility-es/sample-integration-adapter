package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.dao.TrainDao;
import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.*;
import com.appearnetworks.aiq.ia.model.mobile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@Component
public class TrainManagerImpl implements TrainManager {

    @Autowired
    private TrainDao trainDao;

    @Override
    public Train find(String trainId) throws NoSuchDataObjectException {
        return convertToTrain(trainDao.find(trainId));
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

        TrainType trainType = convertToTrainType(trainDO.getTrainTypeDO());



        return new Train(id, rev, number, trainType);
    }


    private TrainType convertToTrainType(TrainTypeDO trainTypeDO) {

        TrainType trainType = new TrainType();
        trainType.setName(trainTypeDO.getName());
        trainType.setDamageCodes(convertToDamageCodes(trainTypeDO.getDamageCodes()));
        trainType.setTrainParts(convertToTrainParts(trainTypeDO.getTrainParts()));

        return trainType;

    }

    private List<DamageCode> convertToDamageCodes(List<DamageCodeDO> damageCodeDOs) {
        List<DamageCode> damageCodes = new ArrayList<>();
        for(DamageCodeDO damageCodeDO : damageCodeDOs) {
            damageCodes.add(convertToDamageCode(damageCodeDO));
        }
        return damageCodes;
    }

    private DamageCode convertToDamageCode(DamageCodeDO damageCodeDO) {
        List<SubCode1> subCode1s = new ArrayList<>();

        for (SubCode1DO subCode1DO : damageCodeDO.getSubCode1DOs()) {
            List<SubCode2> subCode2s = new ArrayList<>();
            for (SubCode2DO subCode2DO : subCode1DO.getSubCode2DOs()) {
                subCode2s.add(new SubCode2(subCode2DO.getId(), subCode2DO.getName()));
            }
            subCode1s.add(new SubCode1(subCode1DO.getName(), subCode2s));
        }

        return new DamageCode(damageCodeDO.getName(), subCode1s);
    }

    private List<TrainPart> convertToTrainParts(List<TrainPartDO> trainPartDOs) {
        List<TrainPart> trainParts = new ArrayList<>();
        for(TrainPartDO trainPartDO : trainPartDOs) {
            trainParts.add(convertToTrainPart(trainPartDO));
        }

        return trainParts;
    }

    private TrainPart convertToTrainPart(TrainPartDO trainPartDO) {
        TrainPart trainPart = new TrainPart();
        trainPart.setName(trainPartDO.getName());
        trainPart.setValue(trainPartDO.getValue());
        return trainPart;
    }

}
