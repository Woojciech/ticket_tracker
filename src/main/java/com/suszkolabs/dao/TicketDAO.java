package com.suszkolabs.dao;

import com.suszkolabs.entity.Ticket;
import java.util.List;

public interface TicketDAO {

    //TODO - fetch all completedTickets by completion
    void saveTicket(Ticket ticket);
    Ticket findTicketById(int ticketId);
    void updateTicket(Ticket ticket);
    void deleteTicket(int ticketId);

    List<Ticket> getAllTickets();
    List<Ticket> getLimitedTicketsByCompletion(boolean isCompleted, int limit);
    List<Ticket> getTicketsByCompletionPaginate(boolean isCompleted, int pageSize, int pageNumber);
    List<Ticket> getUnitTicketsByCompletionPaginate(int unitId, boolean isCompleted, int pageSize, int pageNumber);

    Long countTicketsByCompletion(boolean isCompleted);
    Long countUnitTicketsByCompletion(int unitId, boolean isCompleted);
}
