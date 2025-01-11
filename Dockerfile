FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/demoProjet-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9123

ENTRYPOINT ["java", "-jar", "app.jar"]