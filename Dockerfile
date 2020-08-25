FROM openjdk:11

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar"]

CMD ["-Dserver.port=80", "/app.jar"]
