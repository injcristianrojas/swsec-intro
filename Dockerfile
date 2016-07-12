FROM alpine:latest

RUN echo "@edge http://nl.alpinelinux.org/alpine/edge/main" >> /etc/apk/repositories
RUN echo "@testing http://nl.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories
RUN apk add --update bash openjdk8 maven@testing vim nano

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN ["mvn", "site", "install"]

EXPOSE 8080
CMD ["mvn", "install", "jetty:run"]
