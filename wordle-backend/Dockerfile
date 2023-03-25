FROM gradle:latest AS build

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN gradle bootJar

FROM openjdk:latest

ENV SERVER_PORT 8080
EXPOSE $SERVER_PORT

COPY --from=build /usr/src/app/build/libs/*.jar app.jar

CMD java -jar app.jar
