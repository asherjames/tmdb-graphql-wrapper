import ash.java.graphql.schema.GenreObjectTypes;
import ash.java.graphql.schema.MovieObjectTypes;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

public class GenreQueryTest {

    @Test
    public void correctQueryShouldNotReturnNull() {
        Object result = GenreObjectTypes.executeGenreQuery("result{title}");

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(HashMap.class);
    }

    @Test
    public void correctQueryShouldReturnHashmap() {
        Object result = GenreObjectTypes.executeGenreQuery("{title}");

        HashMap<String, Object> helloMap = (HashMap)result;

        assertThat(helloMap).containsOnly(entry("title", "Pulp Fiction"));
    }
}
