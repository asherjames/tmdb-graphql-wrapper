package ash.java.graphql.data;

import ash.java.graphql.types.TvSeasonType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvDaoImpl implements TvDao {

    @Override
    public List<TvSeasonType> getTvSeason(int tvShowId, int seasonNumber) {
        return null;
    }
}
