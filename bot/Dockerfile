FROM openjdk:21
WORKDIR /app
VOLUME bot/target/bot.jar /app/bot.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "bot.jar"]
