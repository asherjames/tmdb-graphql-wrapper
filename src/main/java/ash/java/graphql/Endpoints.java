package ash.java.graphql;

import ash.java.graphql.schema.GenreObjectTypes;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Component
@Path("/")
public class Endpoints {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndpoint() {
        return Response.ok("GET endpoint").build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEndpoint(String query) {
        Map<String, Object> result = GenreObjectTypes.executeGenreQuery(query);
        String response = new Gson().toJson(result);

        return Response.ok(response).build();
    }
}
