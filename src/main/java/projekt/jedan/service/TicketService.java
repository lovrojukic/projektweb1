package projekt.jedan.service;

import projekt.jedan.entity.Ticket;
import projekt.jedan.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String vatin, String firstName, String lastName) throws Exception {
        if (ticketRepository.countByVatin(vatin) >= 3) {
            throw new Exception("Max tickets for this OIB reached");
        }

        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());
        ticket.setVatin(vatin);
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public long getTicketCount() {
        return ticketRepository.count();
    }

    public Ticket getTicketById(String id) throws Exception {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(() -> new Exception("Ticket not found"));
    }
}
