import ash.java.graphql.data.TmdbSearcher;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.junit.Test;

import static ash.java.graphql.data.TmdbSearcher.TmdbUrl.*;
import static org.assertj.core.api.Assertions.*;

public class TmdbSearcherTest {

    @Test
    public void tmdbSearcherCanRequestGenres() {
        HttpResponse<JsonNode> response = TmdbSearcher.sendRequest(GENRE_LIST_URL);

        assertThat(response.getStatus()).isEqualTo(200);
    }
}
