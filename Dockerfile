FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY build/libs/gcpdemo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
