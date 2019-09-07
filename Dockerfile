# Build using docker build -t jenkins-zaproxy .

FROM jenkins/jenkins:lts-alpine

USER root

RUN wget https://github.com/zaproxy/zaproxy/releases/download/v2.8.1/ZAP_2.8.1_Linux.tar.gz
RUN tar -xzvf ZAP_2.8.1_Linux.tar.gz -C /opt/
RUN mv /opt/ZAP_2.8.1 /opt/zaproxy
RUN ls /opt/zaproxy
