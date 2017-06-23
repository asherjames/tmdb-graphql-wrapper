# TMDb GraphQL wrapper [![Build Status](https://travis-ci.org/asherjames/tmdb-graphql-wrapper.svg?branch=master)](https://travis-ci.org/asherjames/tmdb-graphql-wrapper)
Basic experimentation project looking at how to wrap a RESTful API with a GraphQL proxy.

#### Api key
Add an `apiKey.properties` file in src/main/resources with an entry for `apikey`, or set a `TMDB_API_KEY` environment variable

#### Build

`./gradlew build`


#### Test

`./gradlew test`  
  
<br/>

#### Functions

##### [Genre list](#genrelist)
##### [Film keyword list](#keywordlist)
##### [Movie search](#moviesearch)
##### [Multi-search](#multisearch)
##### [Tv Season search](#tvseasonsearch)

<br/>

#### <a name="genrelist">Genre list</a>

*Query* - `{genres{name}}`

*URL (percent encoded)* - `http://localhost:8080/graphql?query=%7Bgenres%7Bname%7D%7D`

*Result* - 
```json
{
  "genres": [
    {
      "name": "Action"
    },
    {
      "name": "Adventure"
    },
    ...
    {
      "name": "War"
    },
    {
      "name": "Western"
    }
  ]
}
```

#### <a name="keywordlist">List of keywords for film</a>

*Query* - `{keywordList(filmId: "123") {name}}`

*URL (percent encoded)* - `http://localhost:8080/graphql?query=%7BkeywordList%28filmId%3A%22123%22%29%7Bname%7D%7D`

*Result* - 
```json
{
  "keywordList": [
    {
      "name": "elves"
    },
    {
      "name": "dwarves"
    },
    {
      "name": "hobbit"
    },
    {
      "name": "mission"
    }
  ]
}
```

#### <a name="moviesearch">Search for movies</a>

*Query* - `{movieSearch(query: "Das Boot"){release_date title popularity vote_count}}`

*URL (percent encoded)* - `http://localhost:8080/graphql?query=%7BmovieSearch%28query%3A+%22Das+Boot%22%29%7Brelease_date+title+popularity+vote_count%7D%7D`

*Result* - 
```json
{
 "movieSearch": [
   {
     "release_date": "1981-09-16",
     "title": "Das Boot",
     "popularity": 3.213765,
     "vote_count": 501
   },
   {
     "release_date": "1981-04-25",
     "title": "The Boat Is Full",
     "popularity": 1.005289,
     "vote_count": 2
   },
   {
     "release_date": "2007-11-30",
     "title": "Mnozil Brass - Das Trojanische Boot",
     "popularity": 1.000929,
     "vote_count": 1
   },
   ...
  ]
}
```

#### <a name="multisearch">Multi-search</a>

*Query* - 
```
{
    multiSearch(query: "Alien"){
        ... on Person {name} 
        ... on Movie {overview genreIds originalLanguage}
        ... on TvShow{popularity firstAirDate originalName}
    }
}            
```

*URL (percent encoded)* - http://localhost:8080/graphql?query=%7BmultiSearch%28query%3A+%22alien%22%29%7B...+on+Person+%7Bname%7D+...+on+Movie+%7Boverview+genreIds+originalLanguage%7D+...+on+TvShow%7Bpopularity+firstAirDate+originalName%7D%7D%7D+

*Result* -
```json
{
"multiSearch": [
      {
        "name": "Sigourney Weaver"
      }
      ...
      {
        "overview": "During its return to the earth, commercial spaceship Nostromo intercepts a distress signal from a distant planet. When a three-member team of the crew discovers a chamber containing thousands of eggs on the planet, a creature inside one of the eggs attacks an explorer. The entire crew is unaware of the impending nightmare set to descend upon them when the alien parasite planted inside its unfortunate host is birthed.",
        "genreIds": [
          27,
          28,
          53,
          878
        ],
        "originalLanguage": "en"
      }
      ...
      {
        "popularity": 1.921429,
        "firstAirDate": "2005-01-23",
        "originalName": "Pet Alien"
      }
}
```

#### <a name="tvseasonsearch">Tv Season search</a>

*Query* -
```
{
    tvSeasonSearch(tvId: 1408, seasonNum: 5) {
        episodes {
            overview
            crew {
                name
                job
            }
            guestStars {
                name
                character
            }
        }
    }    
}
```

*URL (percent encoded)* - http://localhost:8080/graphql?query=%7BtvSeasonSearch%28tvId%3A+1408%2C+seasonNum%3A+5%29+%7Bepisodes+%7Boverview+crew+%7Bname+job%7DguestStars+%7Bname+character%7D%7D%7D%7D

*Result* - 
```json
{
 "tvSeasonSearch": {
    "episodes": [
        {
            "overview": "In the aftermath of personal tragedy, Wilson resigns from the hospital... and from his friendship with House. Meanwhile, Thirteen struggles with her personal medical problems while helping treat an executive assistant with a similar situation to her own.",
            "crew": [
                {
                    "name": "Eli Attie",
                    "job": "Writer"
                },
                {
                    "name": "Deran Sarafian",
                    "job": "Director"
                }
            ],
            "guestStars": [
                {
                    "name": "Jamie Rose",
                    "character": ""
                },
                {
                    "name": "Christine Woods",
                    "character": ""
                }
            ]
        },
        ...
        {
            "overview": "The team deals with an organ donor whose organs prove fatal, and the two surviving patients. Meanwhile, House hires a private detective to spy on Wilson, but hears a few things about himself that he'd rather not.",
            "crew": [
                {
                    "name": "David Straiton",
                    "job": "Director"
                },
                {
                    "name": "Lawrence Kaplow",
                    "job": "Writer"
                },
                {
                    "name": "David Shore",
                    "job": "Writer"
                }
            ],
            "guestStars": [
                {
                    "name": "Michael Weston",
                    "character": ""
                },
                {
                    "name": "Tim Conlon",
                    "character": ""
                },
                {
                    "name": "Felicia Day",
                    "character": ""
                }
            ]
        }
}
```
