FROM openjdk:11.0.8-jre
MAINTAINER  Matheus Machado Guerzoni Duarte
ENTRYPOINT ["java", "-jar", "/app/orla-project-1.0.0.jar"]
WORKDIR /app
EXPOSE 8080
COPY configuration/build/libs/orla-project-1.0.0.jar  /app/orla-project-1.0.0.jar
