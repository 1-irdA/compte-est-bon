FROM openjdk

COPY compte-est-bon.jar /app/game.jar

WORKDIR /app/

CMD ["java", "-jar", "game.jar"]