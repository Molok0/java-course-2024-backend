FROM openjdk:21
WORKDIR /app
VOLUME scrapper/target/scrapper.jar /app/scrapper.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "scrapper.jar"]
