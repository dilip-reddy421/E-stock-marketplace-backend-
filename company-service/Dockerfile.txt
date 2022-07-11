FROM openjdk:8-jdk-alpine

COPY target/company-service-0.0.1-SNAPSHOT.jar company-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/company-service-0.0.1-SNAPSHOT.jar"]