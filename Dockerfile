# Use the base Render image
FROM render/base:1

# Build stage
FROM openjdk:17-jdk-slim AS build

COPY . /app

WORKDIR /app

# Perform the necessary build steps
RUN ./gradlew build

# Final stage
FROM openjdk:17-jdk-slim

COPY --from=build /app/build/libs/river_data_nimh.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]