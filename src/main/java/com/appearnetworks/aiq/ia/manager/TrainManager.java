package com.appearnetworks.aiq.ia.manager;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.manager.exception.TrainNotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.Train;

import java.util.List;

/**
 * This interface is used by controllers to manage all operations on train data. The implementations of this interface
 * sits between the data object layer and the controllers and provide facilities to convert data transfer objects to
 * data objects and vice versa.
 */
public interface TrainManager {

    /**
     * Uses the underlying data access object to find a train by ID.
     *
     * @param id the id of the train.
     * @return the train damage report.
     * @throws TrainNotFoundException thrown if no train damage report exists for the supplied ID.
     */
    Train find(String id) throws TrainNotFoundException;

    /**
     * Uses the underlying data access object to get all trains.
     *
     * @return a list of all trains.
     */
    List<Train> getAll();
}
