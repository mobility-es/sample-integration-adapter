package com.appearnetworks.aiq.ia.dataaccess.manager;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface TrainDamageReportManager {
    void create(TrainDamageReport trainDamageReport);
    TrainDamageReport find(String trainDamageReportId) throws NoSuchDataObjectException;
    List<TrainDamageReport> getAll();
}
