FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
RUN --mount=type=bind,source=pom.xml,target=/tmp/pom.xml cp /tmp/pom.xml .
COPY pom.xml ./kafka-consumer/pom.xml
COPY kafka-consumer/pom.xml ./kafka-consumer/
COPY kafka-consumer/src ./kafka-consumer/src
RUN mvn -e -f kafka-consumer/pom.xml dependency:go-offline -B
RUN mvn -e -f kafka-consumer/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/kafka-consumer/target/kafka-consumer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]