FROM vyolin/alpine-maven
MAINTAINER Cristián Rojas

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN ["mvn", "site", "install"]

EXPOSE 8080
CMD ["mvn", "install", "jetty:run"]
