package com.appearnetworks.aiq.ia.dataaccess.dao;

import com.appearnetworks.aiq.ia.dataaccess.model.DamageCodeDO;
import com.appearnetworks.aiq.ia.dataaccess.model.TrainDamageReportDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class DamageCodeDaoImpl implements DamageCodeDao {

    private final Map<String, DamageCodeDO> damageCodeMap = new ConcurrentHashMap<String, DamageCodeDO>();

    @Override
    public void create(DamageCodeDO damageCodeDO) {
        damageCodeMap.put(damageCodeDO.getId(), damageCodeDO);
    }

    @Override
    public List<DamageCodeDO> getAll() {
        return new ArrayList<>(damageCodeMap.values());
    }
}
