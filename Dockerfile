FROM openjdk:17-jdk-slim

LABEL maintainer="Hanamizu <m@hanamizu.cn>"

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

EXPOSE 8080

ENV JAVA_OPTS=""

CMD ["sh", "-c", "java $JAVA_OPTS -jar target/CampusHelp-0.0.1-SNAPSHOT.jar"]
