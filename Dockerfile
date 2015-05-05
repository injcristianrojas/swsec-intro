FROM maven:onbuild
MAINTAINER Cristián Rojas

VOLUME /usr/src/app
WORKDIR /usr/src/app
RUN mvn site
EXPOSE 8080
# Execute the three commands, because the database and site stuff may not be in the host machine.
CMD ["mvn", "install", "jetty:run"]
