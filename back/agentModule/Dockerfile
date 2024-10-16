FROM openjdk:17
MAINTAINER gengjiale
COPY *.jar /app.jar

CMD ["--server.port=8079"]

EXPOSE 8079

ENTRYPOINT ["java","-jar","/app.jar"]
