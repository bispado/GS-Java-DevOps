# Dockerfile para aplicação Java Spring Boot
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY gs/target/gs-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 