FROM openjdk:17
MAINTAINER gengjiale
COPY *.jar /userModule.jar

CMD ["--server.port=8081"]

EXPOSE 8081

ENTRYPOINT ["java","-jar","/userModule.jar"]
