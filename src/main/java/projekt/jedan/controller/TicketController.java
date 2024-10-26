package projekt.jedan.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import projekt.jedan.entity.Ticket;
import projekt.jedan.service.QRCodeGenerator;
import projekt.jedan.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(
            @RequestBody Ticket ticketRequest,
            @AuthenticationPrincipal Jwt jwt
    ) {
        try {

            Ticket ticket = ticketService.createTicket(ticketRequest.getVatin(), ticketRequest.getFirstName(), ticketRequest.getLastName());

            String ticketUrl = "https://projektweb1-1.onrender.com/ticket.html?id=" + ticket.getId();


            byte[] qrCode = QRCodeGenerator.generateQRCodeImage(ticketUrl, 250, 250);


            return ResponseEntity.ok().header("Content-Type", "image/png").body(qrCode);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @GetMapping("/count")
    public ResponseEntity<?> getTicketCount() {
        try {
            long count = ticketService.getTicketCount();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving ticket count");
        }
    }


    @GetMapping("/view/{id}")
    public Map<String, Object> getTicket(@PathVariable String id, @AuthenticationPrincipal OidcUser user) throws Exception {
        Ticket ticket = ticketService.getTicketById(id);


        Map<String, Object> response = new HashMap<>();
        response.put("vatin", ticket.getVatin());
        response.put("firstName", ticket.getFirstName());
        response.put("lastName", ticket.getLastName());
        response.put("createdAt", ticket.getCreatedAt());
        response.put("currentUser", user.getFullName());

        return response;
    }


}
