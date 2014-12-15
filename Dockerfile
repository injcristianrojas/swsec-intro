FROM niaquinto/maven
MAINTAINER Cristián Rojas

COPY . /code
WORKDIR /code
RUN mvn clean install
