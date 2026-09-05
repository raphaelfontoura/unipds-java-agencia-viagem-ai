package dev.ia.travel;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/travel")
public class TravelAgentResource {

    // @Inject
    // PackageExpert assistant;

    @Inject 
    PackageExpertWithTemplate assistant;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ask(String question, @HeaderParam("X-User-Name") String userName) {
        if (userName != null && !userName.isEmpty()) {
            // try {
            //     SecurityContext.setCurrentUser(userName);
            //     return assistant.chat(userName, question, userName);
            // } finally {
            //     SecurityContext.clear();
            // }
            return assistant.chat(userName, question, userName);
        } else {
            return "Usuário precisa estar autenticado!";
        }
    }

}
