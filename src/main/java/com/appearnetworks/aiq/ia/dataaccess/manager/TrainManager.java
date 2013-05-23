package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.model.mobile.Train;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainManager {
    Train find(String trainId) throws NoSuchDataObjectException;
    List<Train> getAll();
}
