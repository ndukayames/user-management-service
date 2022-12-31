FROM openjdk:17-jdk
VOLUME /tmp
WORKDIR /app
EXPOSE 8080
COPY ./target/User-Management-Service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","User-Management-Service-0.0.1-SNAPSHOT.jar", "--spring.config.location=classpath:/application.docker.yml"]