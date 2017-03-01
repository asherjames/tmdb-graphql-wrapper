import ash.java.graphql.schema.GenreObjectTypes;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class GenreQueryTest {

    @Test
    public void correctQueryShouldNotReturnNull() {
        Map<String, Object> result = GenreObjectTypes.executeGenreQuery("{genres{id name}}");

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Map.class);
    }

    @Test
    public void correctQueryShouldReturnHashmap() {
        Map<String, Object> result = GenreObjectTypes.executeGenreQuery("{genres{id name}}");

//        assertThat(result).containsOnly(entry("title", "Pulp Fiction"));
    }
}
