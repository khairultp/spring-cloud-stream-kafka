FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
RUN --mount=type=bind,source=pom.xml,target=/tmp/pom.xml cp /tmp/pom.xml .
COPY pom.xml ./kafka-producer/pom.xml
COPY kafka-producer/pom.xml ./kafka-producer/
COPY kafka-producer/src ./kafka-producer/src
RUN mvn -e -f kafka-producer/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/kafka-producer/target/kafka-producer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]