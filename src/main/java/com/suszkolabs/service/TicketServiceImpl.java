package com.suszkolabs.service;

import com.suszkolabs.dao.TicketDAO;
import com.suszkolabs.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    @Transactional
    public void saveTicket(Ticket ticket) {

    }

    @Override
    @Transactional
    public Ticket findTicketById(int ticketId) {
        return null;
    }

    @Override
    @Transactional
    public void updateTicket(Ticket ticket) {

    }

    @Override
    @Transactional
    public void deleteTicket(int ticketId) {

    }

    @Override
    @Transactional
    public List<Ticket> findAllTickets() {
        return null;
    }
}
