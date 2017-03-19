package ash.java.graphql.data;

import static ash.java.graphql.ApiKeyManager.getKey;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TmdbSearcher {

    private static Logger log = LoggerFactory.getLogger(TmdbSearcher.class);

    private static final String API_KEY = getKey();

    private TmdbSearcher(){}

    public enum TmdbUrl {
        GENRE_LIST_URL("https://api.themoviedb.org/3/genre/movie/list");

        private final String url;

        TmdbUrl(String url) {
            this.url = url;
        }
    }

    public enum TmdbArgUrl {
        MOVIE_KEYWORDS_URL("https://api.themoviedb.org/3/movie/", "/keywords");

        private final String firstHalf;
        private final String secondHalf;

        TmdbArgUrl(String first, String second) {
            firstHalf = first;
            secondHalf = second;
        }
    }

    public enum TmdbQueryUrl {
        MOVIE_SEARCH_URL("https://api.themoviedb.org/3/search/movie");

        private String url;

        TmdbQueryUrl(String url) {
            this.url = url;
        }
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbUrl tmdbUrl) {
        return sendTmdbRequest(tmdbUrl.url);
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbArgUrl tmdbArgUrl, String arg) {
        return sendTmdbRequest(tmdbArgUrl.firstHalf + arg + tmdbArgUrl.secondHalf);
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbQueryUrl tmdbQueryUrl, Map<String, Object> queryParams) {
        return sendTmdbRequest(tmdbQueryUrl.url, queryParams);
    }

    private static HttpResponse<JsonNode> sendTmdbRequest(String url) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url)
                    .queryString("api_key", API_KEY)
                    .asJson();
        } catch (UnirestException e) {
            log.error("Error while sending request", e);
        }

        return response;
    }

    private static HttpResponse<JsonNode> sendTmdbRequest(String url, Map<String, Object> queryParams) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url)
                    .queryString("api_key", API_KEY)
                    .queryString(queryParams)
                    .asJson();
        } catch (UnirestException e) {
            log.error("Error while sending request", e);
        }

        return response;
    }
}
