FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Setting up work directory
WORKDIR /app

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/restapi-0.0.1-SNAPSHOT.jar.original /app

EXPOSE 8080
ENTRYPOINT ["java","-jar","restapi-0.0.1-SNAPSHOT.jar.original"]