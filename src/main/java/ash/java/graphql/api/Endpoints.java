package ash.java.graphql.api;

import ash.java.graphql.schema.GenreObjectTypes;
import ash.java.graphql.schema.KeywordObjectTypes;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
    @Path("/genres")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response genresEndpoint(String query) {
        Object result = GenreObjectTypes.executeGenreQuery(query);
        String response = new Gson().toJson(result);

        return Response.ok(response).build();
    }

    @POST
    @Path("/keywords")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response keywordsEndpoint(String query) {
        Object result = KeywordObjectTypes.executeKeywordQuery(query);
        String response = new Gson().toJson(result);

        return Response.ok(response).build();
    }
}
