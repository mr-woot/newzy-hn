FROM openjdk:8-jdk-alpine
RUN mkdir /NEWZY
WORKDIR /NEWZY
COPY target/newzy.jar /NEWZY
RUN cd /NEWZY
ENTRYPOINT ["java", "-Dspring.backgroundpreinitializer.ignore=true", "-Djava.security.egd=file:/dev/./urandom", "-jar", "newzy.jar"]
