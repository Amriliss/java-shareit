FROM amazoncorretto:11-alpine-jdk
FROM openjdk:11
COPY target/*.jar server.jar
ENTRYPOINT ["java","-jar","/server.jar"]