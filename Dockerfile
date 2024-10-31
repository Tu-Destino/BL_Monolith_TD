FROM amazoncorretto:18-alpine-jdk

COPY target/BL_Monolith_TD-0.0.1-SNAPSHOT.jar appli.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/appli.jar"]