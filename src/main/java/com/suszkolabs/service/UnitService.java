package com.suszkolabs.service;

import com.suszkolabs.entity.Unit;

import java.util.List;

public interface UnitService {

    void saveUnit(Unit unit);
    Unit findUnitById(int unitId);
    void updateUnit(Unit unit);
    void deleteUnit(int unitId);
    List<Unit> findAllUnits();

}
