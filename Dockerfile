FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

RUN echo "DATABASE_URL: $DATABASE_URL"
RUN echo "DATABASE_USER: $DATABASE_USER"
RUN echo "DATABASE_PASSWORD: $DATABASE_PASSWORD"

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/restapi-0.0.1-SNAPSHOT.jar restapi.jar
RUN ls -la
EXPOSE 8080
ENTRYPOINT ["java","-jar","restapi.jar"]