package ash.java.graphql.test.schemas;

import ash.java.graphql.types.MovieType;
import ash.java.graphql.types.PersonType;
import ash.java.graphql.types.TvShowType;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTypeInstances {

    public static MovieType getMovie() {
        MovieType movie = new MovieType();
        movie.setPosterPath("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg");
        movie.setAdult(false);
        movie.setOverview("A German submarine hunts allied ships...");
        movie.setReleaseDate("1981-09-16");
        movie.setGenreIds(Stream.of(28, 18, 36, 10752, 12).collect(Collectors.toList()));
        movie.setId(387);
        movie.setOriginalTitle("Das Boot");
        movie.setOriginalLanguage("de");
        movie.setTitle("Das Boot");
        movie.setBackdropPath("/mc0PbKrrFRCUEpI09reR3ihHrIo.jpg");
        movie.setPopularity(3.495501);
        movie.setVoteCount(501);
        movie.setVideo(false);
        movie.setVoteAverage(7.9);

        return movie;
    }

    public static PersonType getPerson() {
        PersonType person = new PersonType();

        return person;
    }

    public static TvShowType getTvShow() {
        TvShowType tvShowType = new TvShowType();

        return tvShowType;
    }
}
