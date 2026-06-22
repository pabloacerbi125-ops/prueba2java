# Multi-stage Dockerfile for project-service
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace
COPY pom.xml .
COPY .mvn .mvn/
COPY mvnw .
COPY auth-service/ auth-service/
COPY project-service/ project-service/
COPY eureka-server/ eureka-server/
COPY api-gateway/ api-gateway/
COPY collab-service/ collab-service/
COPY resource-service/ resource-service/
RUN mvn -f pom.xml -pl project-service -am -B clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /workspace/project-service/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
