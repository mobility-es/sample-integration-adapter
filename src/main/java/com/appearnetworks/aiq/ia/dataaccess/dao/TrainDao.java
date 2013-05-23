package com.appearnetworks.aiq.ia.dataaccess.dao;


import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.model.mobile.Train;

import java.util.List;

/**
 * To be used for CRUD operations related to train damage reports.
 *
 */
public interface TrainDao {

    /**
     * Method to create a new train.
     *
     * @param trainDO This object contains train data that is to be persisted.
     */
    void create(TrainDO trainDO);

    /**
     * Method to find a train by its ID.
     *
     * @param id the id of the train.
     * @return the persisted train object
     * @throws NoSuchDataObjectException
     */
    TrainDO find(String id) throws NoSuchDataObjectException;

    /**
     * Method to return all trains.
     *
     * @return a list of all trains.
     */
    List<TrainDO> getAll();
}
