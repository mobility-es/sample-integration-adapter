package com.appearnetworks.aiq.ia.dataaccess.dao;


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
     * Get all trains
     * @return
     */
    List<Train> getAll();
}
