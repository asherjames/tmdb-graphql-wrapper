# TMDb GraphQL wrapper [![Build Status](https://travis-ci.org/asherjames/tmdb-graphql-wrapper.svg?branch=master)](https://travis-ci.org/asherjames/tmdb-graphql-wrapper)
Basic experimentation project looking at how to wrap a RESTful API with a GraphQL proxy.
  
So far interprets simple genre and keyword requests:

Endpoint - `/genres`

Query - `{genres{name}}`

URL (percent encoded) = `http://localhost:8080/genres?query=%7Bgenres%7Bname%7D%7D`

Result - 
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


Endpoint - `/keywords`

Query - `{keywordList(filmId: "123") {name}`

URL (percent encoded) - `http://localhost:8080/keywords?query=%7BkeywordList%28filmId%3A%22123%22%29%7Bname%7D`

Result - 
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

###Api key
Add an `apiKey.properties` file in src/main/resources with an entry for `apikey`, or set a `TMDB_API_KEY` environment variable

###Build

`./gradlew build`


###Test

`./gradlew test`
