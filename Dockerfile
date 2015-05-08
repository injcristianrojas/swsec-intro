FROM maven:onbuild
MAINTAINER Cristián Rojas

RUN ["mvn", "site"]

EXPOSE 8080
CMD ["mvn", "install", "jetty:run"]
