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

    @GET
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response genresEndpoint(@QueryParam("query") String query) {
        DataObject result = new DataObject(GenreObjectTypes.executeGenreQuery(query));
        String response = new Gson().toJson(result);

        return Response.ok(response).build();
    }

    @GET
    @Path("/keywords")
    @Produces(MediaType.APPLICATION_JSON)
    public Response keywordsEndpoint(@QueryParam("query") String query) {
        DataObject result = new DataObject(KeywordObjectTypes.executeKeywordQuery(query));
        String response = new Gson().toJson(result);

        return Response.ok(response).build();
    }
}
