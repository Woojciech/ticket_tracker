package com.suszkolabs.dao;

import com.suszkolabs.entity.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTicket(Ticket ticket) {

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
    public List<Ticket> findAllTickets() {
        return null;
    }
}
