package projekt.jedan.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class IdHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String ticketId = (String) request.getSession().getAttribute("ticketId");

        if (ticketId != null) {
            response.sendRedirect("/tickets/ticket/view/" + ticketId);
        } else {
            response.sendRedirect("/ticket.html");
        }
    }
}
