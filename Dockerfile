FROM niaquinto/maven
MAINTAINER Cristián Rojas

RUN printf '\nexport TERM="xterm-256color"' >> /root/.bashrc
RUN apt-get install -y emacs23-nox nano

COPY . /app
WORKDIR /app

RUN mvn clean install site

EXPOSE 8080
CMD ["jetty:run"]