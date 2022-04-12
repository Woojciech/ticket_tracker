package com.suszkolabs.service;

import com.suszkolabs.dao.TicketDAO;
import com.suszkolabs.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    @Transactional
    public void saveTicket(Ticket ticket) {
        ticketDAO.saveTicket(ticket);
    }

    @Override
    @Transactional
    public Ticket findTicketById(int ticketId) {
        return ticketDAO.findTicketById(ticketId);
    }

    @Override
    @Transactional
    public void updateTicket(Ticket ticket) {

    }

    @Override
    @Transactional
    public void deleteTicket(int ticketId) {
        ticketDAO.deleteTicket(ticketId);
    }

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    @Transactional
    public List<Ticket> getLimitedTicketsByCompletion(int limit) {
        List<Ticket> completed = ticketDAO.getLimitedTicketsByCompletion(true, limit);
        List<Ticket> uncompleted = ticketDAO.getLimitedTicketsByCompletion(false, limit);

        List<Ticket> mergedTickets = Stream.concat(completed.stream(), uncompleted.stream()).collect(Collectors.toList());

        return mergedTickets;
    }

    @Override
    @Transactional
    public List<Ticket> getTicketsByCompletionPaginate(boolean isCompleted, int pageSize, int pageNumber) {
        return ticketDAO.getTicketsByCompletionPaginate(isCompleted, pageSize, pageNumber);
    }

    @Override
    @Transactional
    public Long countTicketsByCompletion(boolean isCompleted) {
        return ticketDAO.countTicketsByCompletion(isCompleted);
    }

    @Override
    @Transactional
    public void changeCompletionStatus(int ticketId, boolean currentStatus){
        Ticket ticket = ticketDAO.findTicketById(ticketId);
        ticket.setCompleted(!currentStatus);
        ticketDAO.updateTicket(ticket);
    }

}
