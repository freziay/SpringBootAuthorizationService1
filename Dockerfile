FROM adoptopenjdk/openjdk:8-jdk-alpine-jre
EXPOSE 8080
COPY target/demoSQL-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
