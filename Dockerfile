## Build
FROM amazoncorretto:19.0.1 as builder

WORKDIR /

COPY . .

RUN ./gradlew build


## Deploy
FROM amazoncorretto:19.0.1

COPY --from=builder build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
