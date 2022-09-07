FROM openjdk:16

WORKDIR /app

COPY build/libs/application.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "application.jar"]