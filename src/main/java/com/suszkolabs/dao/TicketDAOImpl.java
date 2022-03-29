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
        return null;
    }

    @Override
    public void updateTicket(Ticket ticket) {

    }

    @Override
    public void deleteTicket(int ticketId) {

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
        Query<Ticket> query = currentSession.createQuery("from Ticket t where t.isCompleted = :isCompleted");
        query.setParameter("isCompleted", isCompleted);
        query.setMaxResults(limit);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByCompletion(boolean isCompleted) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Ticket> query = currentSession.createQuery("from Ticket where isCompleted = :isCompleted");
        query.setParameter("isCompleted", isCompleted);

        return query.getResultList();
    }
}
