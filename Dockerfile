FROM openjdk:8-jdk-alpine
MAINTAINER ToQuery <toquery@qq.com>
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]