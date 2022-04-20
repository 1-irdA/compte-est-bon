FROM openjdk:18

COPY compte-est-bon.jar app/compte.jar

WORKDIR app/

CMD ["java", "-jar", "compte.jar"]