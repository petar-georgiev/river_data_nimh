FROM openjdk:17-jdk-slim AS build

# Copy the necessary build files to a build directory
COPY . /home/gradle/river_nimh_data

WORKDIR /home/gradle/river_nimh_data

# Build the application (if necessary)

# Assuming the build process generates the JAR file at '/home/gradle/river_nimh_data/build/libs/river_data_nimh.jar'

FROM openjdk:17-jdk-slim

COPY --from=build /home/gradle/river_nimh_data/build/libs/river_data_nimh.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]