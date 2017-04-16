package ash.java.graphql.data;

public final class TmdbUrls {
    public enum TmdbUrl {
        GENRE_LIST_URL("https://api.themoviedb.org/3/genre/movie/list");

        final String url;

        TmdbUrl(String url) {
            this.url = url;
        }
    }

    public enum TmdbArgUrl {
        MOVIE_KEYWORDS_URL("https://api.themoviedb.org/3/movie/", "/keywords");

        final String firstHalf;
        final String secondHalf;

        TmdbArgUrl(String first, String second) {
            firstHalf = first;
            secondHalf = second;
        }
    }

    public enum TmdbQueryUrl {
        MOVIE_SEARCH_URL("https://api.themoviedb.org/3/search/movie"),
        MULTI_SEARCH_URL("https://api.themoviedb.org/3/search/multi");

        String url;

        TmdbQueryUrl(String url) {
            this.url = url;
        }
    }
}
