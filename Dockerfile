FROM openjdk:17-jdk-slim AS build

COPY . .

RUN ./gradlew clean build

FROM openjdk:17-jdk-slim

COPY --from=build /target/river_data_nimh.jar river_data_nimh.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","river_data_nimh.jar"]