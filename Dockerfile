FROM openjdk:8

ADD . /opt/tmdb-gql/

WORKDIR /opt/tmdb-gql

RUN ./gradlew clean build

ENTRYPOINT ["java", "-jar", "build/libs/tmdb-gql-0.0.1.jar"]