FROM openjdk:8-jdk-alpine

COPY target/cloud-config-server-0.0.1-SNAPSHOT.jar cloud-config-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/cloud-config-server-0.0.1-SNAPSHOT.jar"]