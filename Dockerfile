FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/app.jar app.jar

LABEL maintainer="jeancbezerra" \
      org.opencontainers.image.source="https://github.com/jeanbezerra/demo-swagger-api" \
      org.opencontainers.image.title="Demo Swagger API" \
      org.opencontainers.image.description="Spring Boot API with Swagger for testing automation tools."

ENV SERVER_PORT=8080

EXPOSE 443
EXPOSE 8080
EXPOSE 10000

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-XX:InitialRAMPercentage=50.0", "-XX:MinRAMPercentage=25.0", "-jar", "app.jar"]
