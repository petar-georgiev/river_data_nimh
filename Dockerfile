# Use the base Render image
FROM render/base:1

# Build stage
FROM gradle:7.2.0-jdk11 as builder

COPY . /home/gradle/river_nimh_data

WORKDIR /home/gradle/river_nimh_data

# Run the Gradle build
RUN ./gradlew build

# Final stage
FROM openjdk:17-jdk-slim

COPY --from=builder /home/gradle/river_nimh_data/build/libs/river_data_nimh.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]