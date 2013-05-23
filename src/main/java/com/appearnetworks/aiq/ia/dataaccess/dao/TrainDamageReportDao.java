package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.exception.NoSuchDataObjectException;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;

import java.util.List;

/**
 * To be used for CRUD operations related to train damage reports.
 *
 */
public interface TrainDamageReportDao {

    /**
     * Method to create a new train damage report.
     *
     * @param trainDamageReportDO This object contains train damage data that is to be persisted.
     * @return the persisted train damage report object.
     */
    TrainDamageReportDO create(TrainDamageReportDO trainDamageReportDO);

    /**
     * Method to return all train damage reports.
     *
     * @return a list of all train damage reports.
     */
    List<TrainDamageReportDO> getAll();

    /**
     * Method to find a train damage report by its ID.
     *
     * @param id the id of train damage report.
     * @return the persisted train damage report object.
     * @throws NoSuchDataObjectException thrown if no train damage report is found for the supplied id.
     */
    TrainDamageReportDO find(String id) throws NoSuchDataObjectException;
}
