FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD JobNest-backend/target/JobNest-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]