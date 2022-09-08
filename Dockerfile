FROM gradle:7.5.1-jdk17-alpine AS build
WORKDIR /app
COPY --chown=gradle:gradle . /app
VOLUME "/app/.gradle"
RUN gradle build --no-daemon

FROM openjdk:17
COPY --from=build /app/build/libs/application.jar /home/application.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
EXPOSE 8080
CMD ["java","-jar","/home/application.jar"]