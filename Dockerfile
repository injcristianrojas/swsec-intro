FROM niaquinto/maven
MAINTAINER Cristián Rojas

RUN export TERM="xterm-256color"
RUN apt-get install -y emacs23-nox nano

COPY . /app
WORKDIR /app

RUN mvn clean install site

EXPOSE 8080
CMD ["jetty:run"]