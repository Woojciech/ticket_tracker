package com.suszkolabs.service;
import com.suszkolabs.dao.UnitDAO;
import com.suszkolabs.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitDAO unitDAO;

    @Override
    @Transactional
    public void saveUnit(Unit unit) {

    }

    @Override
    @Transactional
    public Unit findUnitById(int unitId) {
        return unitDAO.findUnitById(unitId);
    }

    @Override
    @Transactional
    public void updateUnit(Unit unit) {

    }

    @Override
    @Transactional
    public void deleteUnit(int unitId) {

    }

    @Override
    @Transactional
    public List<Unit> getAllUnits() {
        return unitDAO.getAllUnits();
    }

}
