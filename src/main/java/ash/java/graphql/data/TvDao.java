package ash.java.graphql.data;

import ash.java.graphql.types.TvSeasonType;

import java.util.List;

public interface TvDao {

    List<TvSeasonType> getTvSeason(int tvShowId, int seasonNumber);
}
