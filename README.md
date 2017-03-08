# TMDb GraphQL wrapper
Basic experimentation project looking at how to wrap a RESTful API with a GraphQL proxy.
  
So far interprets simple genre and keyword requests:

Endpoint - `/genres`

Query - `{genres{name}}`

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
Add an `apiKey.properties` file in src/main/resources with an entry for `apikey`

###Build

`./gradlew build`


###Test

`./gradlew test`