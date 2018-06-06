FROM maven:alpine

WORKDIR /tmp
ADD pom.xml /tmp/pom.xml
RUN ["mvn", "clean", "compile"]

WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["mvn", "jetty:run"]
