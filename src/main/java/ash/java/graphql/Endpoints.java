package ash.java.graphql;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
public class Endpoints {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndpoint() {
        return Response.ok("GET endpoint").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEndpoint() {
        return Response.ok("POST endpoint").build();
    }
}
