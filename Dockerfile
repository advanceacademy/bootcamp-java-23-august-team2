FROM openjdk:20
ARG JAT_FILE=target/*.jar
COPY ./target/bootcamp-java-23-team2.jar app.jar
#COPY src/main/resources/application.yml /app/application.yml
#COPY src/main/resources/carImages ./src/main/resources/carImages
ENTRYPOINT ["java","-jar","/app.jar"]
