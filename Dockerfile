FROM gradle:7.5.1-jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle build --no-daemon

FROM openjdk:17
COPY --from=build /app/build/libs/application.jar /usr/local/lib/application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/application.jar"]