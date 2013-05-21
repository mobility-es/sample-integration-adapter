package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.model.DamageCodeDO;
import com.appearnetworks.aiq.ia.model.mobile.DamageCode;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public interface DamageCodeDao {
    void create(DamageCodeDO damageCodeDO);
    List<DamageCodeDO> getAll();

}
