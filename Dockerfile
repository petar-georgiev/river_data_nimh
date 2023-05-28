# Use the base Render image
FROM render/base:1

# Install Gradle
RUN apt-get update && apt-get install -y gradle

# Copy the necessary build files to a build directory
COPY . /app

WORKDIR /app

# Build the application
RUN gradle build

FROM openjdk:17-jdk-slim

COPY --from=build /app/build/libs/river_data_nimh.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]