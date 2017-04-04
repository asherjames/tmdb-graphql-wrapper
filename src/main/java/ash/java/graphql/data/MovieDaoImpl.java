package ash.java.graphql.data;

import ash.java.graphql.data.models.Keyword;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MovieDaoImpl implements MovieDao {

    private Gson gson = new Gson();
    private Type keywordListType = new TypeToken<List<Keyword>>(){}.getType();

    @Override
    public List<Keyword> getKeywordsForMovie(int movieId) {
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbArgUrl.MOVIE_KEYWORDS_URL, Integer.toString(movieId));

        String keywords = response.getBody().getObject().get("keywords").toString();

        return gson.fromJson(keywords, keywordListType);
    }
}