FROM openjdk:17-jdk-slim AS build

COPY . /home/gradle/river_nimh_data

WORKDIR /home/gradle/river_nimh_data

RUN gradle build

FROM openjdk:17-jdk-slim

COPY --from=build /home/gradle/river_nimh_data/build/libs/river_data_nimh.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]