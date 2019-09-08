# Build using docker build -t jenkins-zaproxy .

FROM jenkins/jenkins:lts-alpine

USER root

RUN apk --update add --no-cache maven

RUN wget https://github.com/zaproxy/zaproxy/releases/download/v2.8.1/ZAP_2.8.1_Linux.tar.gz
RUN tar -xzvf ZAP_2.8.1_Linux.tar.gz -C /opt/
RUN mv /opt/ZAP_2.8.1 /opt/zaproxy

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

USER jenkins