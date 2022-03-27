package com.suszkolabs.dao;

import com.suszkolabs.entity.Ticket;
import java.util.List;

public interface TicketDAO {

    void saveTicket(Ticket ticket);
    Ticket findTicketById(int ticketId);
    void updateTicket(Ticket ticket);
    void deleteTicket(int ticketId);
    List<Ticket> findAllTickets();
}
