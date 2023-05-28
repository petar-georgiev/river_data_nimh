FROM gradle:6.9.3-jdk11 as cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle clean build -i --stacktrace


# Build stage
FROM gradle:6.9.3-jdk11 as builder

# Copy the Gradle cache
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle

# Copy the application source code
COPY . /usr/src/java-code/
COPY .env /usr/src/java-code/

WORKDIR /usr/src/java-code

# Build the application using Gradle
RUN gradle bootJar -i --stacktrace

# Final stage
FROM openjdk:11-jre-slim

EXPOSE 8080

# Set the user to root
USER root

WORKDIR /usr/src/java-app

# Copy the JAR file from the build stage
COPY --from=builder /usr/src/java-code/build/libs/*.jar ./app.jar

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]