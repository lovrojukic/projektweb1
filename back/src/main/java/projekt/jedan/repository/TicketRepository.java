package projekt.jedan.repository;

import projekt.jedan.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    long countByVatin(String vatin);
}
