FROM ubuntu:precise
MAINTAINER injcristianrojas

RUN apt-get update
RUN apt-get install -y tomcat6 maven