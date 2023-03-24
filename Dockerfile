FROM openjdk:17
ARG jarFile=/target/ESSW-FinalProject-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${jarFile} essw.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","essw.jar"]