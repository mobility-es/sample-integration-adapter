package com.appearnetworks.aiq.ia.manager;

import com.appearnetworks.aiq.ia.manager.exception.NotFoundException;
import com.appearnetworks.aiq.ia.model.mobile.TrainDamageReport;

import java.util.List;

/**
 * This interface is used by controllers to manage all operations on train damage report data. The implementations of
 * this interface sit between the data object layer and the controllers and provide facilities to convert data transfer
 * objects to data objects and vice versa.
 */
public interface TrainDamageReportManager {

    /**
     * Uses the underlying data access object to create a train damage report.
     *
     * @param trainDamageReport the train damage report to be persisted.
     * @return the persisted train damage report.
     */
    TrainDamageReport create(TrainDamageReport trainDamageReport);

    /**
     * Uses the underlying data access object to find a train damage report by ID.
     *
     * @param id the id of the train damage report.
     * @return the train damage report
     * @throws NotFoundException thrown if no train damage report exists for the supplied ID.
     */
    TrainDamageReport find(String id) throws NotFoundException;

    /**
     * Uses the underlying data access object to get all train damage reports.
     *
     * @return a list all train damage reports.
     */
    List<TrainDamageReport> getAll();
}
