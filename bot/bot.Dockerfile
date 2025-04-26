FROM openjdk:21
WORKDIR /app
COPY target/*.jar app.jar
ENV TOKEN=${TOKEN}
EXPOSE 8090
ENTRYPOINT ["java", "-Dapp.telegram-token=${TOKEN}", "-jar", "app.jar"]
