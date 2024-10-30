FROM eclipse-temurin:18.0.2_13-jdk-alpine

COPY target/BL_Monolith_TD-0.0.1-SNAPSHOT.jar appli.jar

ENTRYPOINT ["java", "-jar", "/appli.jar"]