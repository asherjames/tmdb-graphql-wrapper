package ash.java.graphql;

import ash.java.graphql.data.TmdbSearcher;
import ash.java.graphql.schema.GenreSchema;
import ash.java.graphql.schema.KeywordSchema;
import ash.java.graphql.schema.MovieSchema;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.schema.GraphQLObjectType.newObject;

@Service
public class TmdbSchema {

    private GraphQL graphQL;
    private static Logger Log = LoggerFactory.getLogger(TmdbSchema.class);

    @Autowired
    public TmdbSchema(TmdbSearcher searcher) {
        MovieSchema movieSchema = new MovieSchema(searcher);
        KeywordSchema keywordSchema = new KeywordSchema(searcher);
        GenreSchema genreSchema = new GenreSchema(searcher);

        GraphQLObjectType queryType = newObject()
                .name("QueryType")
                .field(movieSchema.getFieldDefinition())
                .field(keywordSchema.getFieldDefinition())
                .field(genreSchema.getFieldDefinition())
                .build();

        GraphQLSchema tmdbSchema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        graphQL = new GraphQL(tmdbSchema);
    }

    public Object executeQuery(String query) {
        Log.info("Executing query: {}", query);
        return graphQL.execute(query);
    }
}
