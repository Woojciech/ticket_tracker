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
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(unit);
    }

    @Override
    public Unit findUnitById(int unitId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Unit> query = currentSession.createQuery("from Unit u where u.id=:id");
        query.setParameter("id", unitId);

        return query.getSingleResult();
    }

    @Override
    public Unit findUnitByIdEager(int unitId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Unit> query = currentSession.createQuery("from Unit u left join fetch u.relatedTickets where u.id=:id");
        query.setParameter("id", unitId);

        return query.getSingleResult();
    }

    @Override
    public void updateUnit(Unit unit) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.update(unit);
    }

    @Override
    public void deleteUnit(int unitId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Unit deletedUnit = findUnitById(unitId);
        currentSession.delete(deletedUnit);
    }

    @Override
    public List<Unit> getAllUnits() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Unit> query = currentSession.createQuery("from Unit");

        return query.getResultList();
    }

    @Override
    public List<Unit> getUnitsPaginate(int pageNumber, int pageSize) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Unit> query = currentSession.createQuery("from Unit");
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public List<Unit> getAllUnitsEager() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Unit> query = currentSession.createQuery("from Unit u left join fetch u.relatedTickets");

        return query.getResultList();
    }

    @Override
    public Long getUnitsCount() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Long> query = currentSession.createQuery("select count(id) from Unit");

        return query.getSingleResult();
    }
}
