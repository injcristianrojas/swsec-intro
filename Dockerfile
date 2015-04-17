FROM maven:onbuild
MAINTAINER Cristián Rojas

VOLUME /usr/src/app

EXPOSE 8080
CMD ["mvn", "install", "jetty:run"]
