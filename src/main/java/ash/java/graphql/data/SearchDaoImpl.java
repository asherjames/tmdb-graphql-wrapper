package ash.java.graphql.data;

import ash.java.graphql.data.models.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchDaoImpl implements SearchDao {

    private Gson gson = new Gson();
    private Type movieSearchListType = new TypeToken<List<Movie>>(){}.getType();

    @Override
    public List<Movie> searchMoviesWithQuery(String query) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("query", query);
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbQueryUrl.MOVIE_SEARCH_URL, queryMap);

        String searchResults = response.getBody().getObject().get("results").toString();

        return gson.fromJson(searchResults, movieSearchListType);
    }

    @Override
    public List<Movie> searchMoviesWithMultipleParameters(Map<String, String> params) {
        return null;
    }
}
