package com.suszkolabs.dao;

import com.suszkolabs.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    //TODO test DAO
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTicket(Ticket ticket) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.save(ticket);
    }

    @Override
    public Ticket findTicketById(int ticketId) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(Ticket.class, ticketId);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.update(ticket);
    }

    @Override
    public void deleteTicket(int ticketId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Ticket deletedTicket = findTicketById(ticketId);
        currentSession.delete(deletedTicket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        Session currentSession = sessionFactory.getCurrentSession();

        List<Ticket> tickets = currentSession.createQuery("from Ticket").getResultList();

        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> getLimitedTicketsByCompletion(boolean isCompleted, int limit) {
        Session currentSession = sessionFactory.getCurrentSession();

        // fetch limited number of queries dependent on their completion type
        Query<Ticket> query = currentSession.createQuery("from Ticket t where t.isCompleted = :isCompleted order by dateAdded desc");
        query.setParameter("isCompleted", isCompleted);
        query.setMaxResults(limit);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByCompletionPaginate(boolean isCompleted, int pageSize, int pageNumber) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Ticket> query = currentSession.createQuery("from Ticket where isCompleted = :isCompleted");
        query.setParameter("isCompleted", isCompleted);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public List<Ticket> getUnitTicketsByCompletionPaginate(int unitId, boolean isCompleted, int pageSize, int pageNumber) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Ticket> query = currentSession.createQuery("from Ticket t where t.isCompleted = :isCompleted and t.relatedUnit.id = :unitId");
        query.setParameter("isCompleted", isCompleted);
        query.setParameter("unitId", unitId);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Long countTicketsByCompletion(boolean isCompleted) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Long> query = currentSession.createQuery("select count(t.id) from Ticket t where t.isCompleted = :isCompleted");
        query.setParameter("isCompleted", isCompleted);

        return query.getSingleResult();
    }

    @Override
    public Long countUnitTicketsByCompletion(int unitId, boolean isCompleted) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Long> query = currentSession.createQuery("select count(t.id) from Ticket t where t.relatedUnit.id = :unitId and t.isCompleted = :isCompleted");
        query.setParameter("unitId", unitId);
        query.setParameter("isCompleted", isCompleted);

        return query.getSingleResult();
    }


}
