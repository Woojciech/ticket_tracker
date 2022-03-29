package com.suszkolabs.dao;

import com.suszkolabs.entity.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitDAOImpl implements UnitDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUnit(Unit unit) {

    }

    @Override
    public Unit findUnitById(int unitId) {
        return null;
    }

    @Override
    public void updateUnit(Unit unit) {

    }

    @Override
    public void deleteUnit(int unitId) {

    }

    @Override
    public List<Unit> getAllUnits() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Unit> query = currentSession.createQuery("from Unit");

        return query.getResultList();
    }
}
