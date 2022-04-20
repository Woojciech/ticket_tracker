package com.suszkolabs.dao;

import com.suszkolabs.entity.Unit;

import java.util.List;

public interface UnitDAO {

    void saveUnit(Unit unit);
    Unit findUnitById(int unitId);
    void updateUnit(Unit unit);
    void deleteUnit(int unitId);
    List<Unit> getAllUnits();
    List<Unit> getUnitsPaginate(int pageNumber, int pageSize);

    // eager fetch
    Unit findUnitByIdEager(int unitId);
    List<Unit> getAllUnitsEager();

    Long getUnitsCount();
}
