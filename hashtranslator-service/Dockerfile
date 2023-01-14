FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/hashtranslator-0.0.1.jar ./hashtranslator.jar
EXPOSE 8081:8081
CMD ["java", "-jar", "/app/hashtranslator.jar"]