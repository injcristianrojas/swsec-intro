FROM maven:onbuild
MAINTAINER Cristián Rojas

RUN mvn site

VOLUME /usr/src/app

EXPOSE 8080

# Execute the three commands, because the database and site stuff may not be in the host machine.
CMD ["mvn", "install", "jetty:run"]
