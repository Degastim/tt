FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/authorization-0.0.1.jar ./authorization.jar
EXPOSE 8080:8080
CMD ["java", "-jar", "/app/authorization.jar"]