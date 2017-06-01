package ash.java.graphql.data;

import ash.java.graphql.types.TvSeasonType;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvDaoImpl implements TvDao {

    @Override
    public List<TvSeasonType> getTvSeason(int tvShowId, int seasonNumber) {
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbTwoArgUrl.TV_SEASON_URL,
                Integer.toString(tvShowId),
                Integer.toString(seasonNumber));


    }
}
