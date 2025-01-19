FROM gradle:8.12.0-jdk17-alpine AS builder
ENV GRADLE_USER_HOME=/home/gradle/.gradle
RUN rm -rf /home/gradle/.gradle/caches/*
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
