FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal
COPY ../target/*.jar demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]
