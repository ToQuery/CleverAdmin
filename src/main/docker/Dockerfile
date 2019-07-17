FROM openjdk:8-jdk-alpine
MAINTAINER ToQuery <toquery@qq.com>

# WORKDIR /app

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]