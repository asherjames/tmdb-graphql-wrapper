package ash.java.graphql.data;

import static ash.java.graphql.ApiKey.getKey;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TmdbSearcher {

    private static Logger log = LoggerFactory.getLogger(TmdbSearcher.class);

    public static final String GENRE_LIST_URL = "https://api.themoviedb.org/3/genre/movie/list";

    public static HttpResponse<JsonNode> sendRequest(String url) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url)
                    .queryString("api_key", getKey())
                    .asJson();
        } catch (UnirestException e) {
            log.error("Error while sending request", e);
        }

        return response;
    }
}
