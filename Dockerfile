FROM openjdk:11

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar"]

CMD ["-Dserver.port=80","/app.jar"]
