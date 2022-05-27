FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE=./target/*.jar
COPY $JAR_FILE ./diversity-lunch.jar

ENTRYPOINT ["java", "-jar", "./diversity-lunch.jar"]
