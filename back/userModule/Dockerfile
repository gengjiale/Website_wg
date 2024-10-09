FROM jdk:17
MAINTAINER gengjiale
COPY *.jar /userModule.jar

CMD ["--server.port=9601"]

EXPOSE 9601

ENTRYPOINT ["java","-jar","/userModule.jar"]
