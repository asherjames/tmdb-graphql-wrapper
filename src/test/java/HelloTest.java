import ash.java.graphql.HelloQuery;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

public class HelloTest {

    @Test
    public void correctQueryShouldNotReturnNull() {
        Object result = HelloQuery.executeHello("result{title}");

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(HashMap.class);
    }

    @Test
    public void correctQueryShouldReturnHashmap() {
        Object result = HelloQuery.executeHello("{title}");

        HashMap<String, Object> helloMap = (HashMap)result;

        assertThat(helloMap).containsOnly(entry("title", "Pulp Fiction"));
    }
}
