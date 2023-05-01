FROM gradle:7.6.1-jdk17 AS build
ENV APP_HOME=/home/gradle/river_nimh_data

WORKDIR $APP_HOME

COPY . .

#COPY build.gradle settings.gradle gradlew ./
#
#COPY gradle ./gradle
#
#COPY src ./src

RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim
ENV APP_HOME=/home/gradle/river_nimh_data
RUN mkdir /app

COPY --from=build $APP_HOME/build/libs/river_data_nimh.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]