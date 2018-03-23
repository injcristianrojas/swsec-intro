FROM maven:3-jdk-8-alpine

COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install

EXPOSE 8080
CMD mvn jetty:run
