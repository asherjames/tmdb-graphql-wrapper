package ash.java.graphql.data;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import static ash.java.graphql.ApiKeyManager.getKey;
import static ash.java.graphql.data.TmdbUrls.*;

@Service
public class TmdbSearcher {

    private static Logger log = LoggerFactory.getLogger(TmdbSearcher.class);

    private static final String API_KEY = getKey();

    public HttpResponse<JsonNode> sendRequest(TmdbUrl tmdbUrl) {
        return sendTmdbRequest(tmdbUrl.url);
    }

    public HttpResponse<JsonNode> sendRequest(TmdbArgUrl tmdbArgUrl, String arg) {
        return sendTmdbRequest(tmdbArgUrl.firstHalf + arg + tmdbArgUrl.secondHalf);
    }

    public HttpResponse<JsonNode> sendRequest(TmdbQueryUrl tmdbQueryUrl, Map<String, Object> queryParams) {
        return sendTmdbRequest(tmdbQueryUrl.url, queryParams);
    }

    private HttpResponse<JsonNode> sendTmdbRequest(String url) {
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

    private HttpResponse<JsonNode> sendTmdbRequest(String url, Map<String, Object> queryParams) {
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
