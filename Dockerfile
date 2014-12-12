FROM ubuntu:precise
MAINTAINER Cristián Rojas "injcristianrojas@gmail.com"

RUN apt-get update
RUN apt-get install -y maven
