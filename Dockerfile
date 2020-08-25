FROM gradle:jdk11 as build

WORKDIR /links-api
COPY ./src ./src
COPY ./gradle ./gradle
COPY ./gradlew ./*.kts ./

RUN ./gradlew bootJar

FROM openjdk:8-jre-slim as prod
ENV PORT 8080
EXPOSE 8080
COPY --from=build /links-api/build/libs/links*.jar /links-api.jar
CMD ["java", "-jar", "/links-api.jar"]