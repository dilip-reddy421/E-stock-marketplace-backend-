FROM openjdk:8-jdk-alpine

COPY target/Api-Gateway-0.0.1-SNAPSHOT.jar Api-Gateway-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/Api-Gateway-0.0.1-SNAPSHOT.jar"]