FROM openjdk

COPY . app/

WORKDIR app/

RUN javac *.java

CMD ["java", "ConsoleGame"]