FROM openjdk:17
MAINTAINER gengjiale
COPY *.jar /articleModule.jar

CMD ["--server.port=8082"]

EXPOSE 8082

ENTRYPOINT ["java","-jar","/articleModule.jar"]
