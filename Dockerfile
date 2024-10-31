FROM amazoncorretto:18-alpine-jdk

WORKDIR /app

COPY target/BL_Monolith_TD-0.0.1-SNAPSHOT.jar /app/appli.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "appli.jar"]