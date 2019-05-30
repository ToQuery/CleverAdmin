FROM alpine/git
MAINTAINER ToQuery <toquery@qq.com>

WORKDIR /app
RUN git clone https://github.com/ToQuery/CleverWeb.git

FROM maven:3.5-jdk-8-alpine
WORKDIR /app
RUN mvn clean install package -DskipTests -X

COPY --from=1 /app/target/*.jar /app/app.jar
ENTRYPOINT ["java -jar app.jar"]
