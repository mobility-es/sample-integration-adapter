package com.appearnetworks.aiq.ia.dataaccess.dao;


import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDO;
import com.appearnetworks.aiq.ia.rest.model.Train;

import java.util.List;

/**
 * This interface is used for CRUD operations related to trains.
 *
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainDao {

    /**
     * Create a new train
     * @param train
     */
    void create(Train train);

    /**
     *
     * @param trainId
     * @return
     */
    TrainDO find(String trainId) throws NoSuchDataObjectException;

    /**
     * Get all trains
     * @return
     */
    List<TrainDO> getAll();
}
