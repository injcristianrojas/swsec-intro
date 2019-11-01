FROM sonarqube:lts

USER root
RUN apt-get update
RUN apt-get install -y wget
RUN rm -rf /var/lib/apt/lists/*
RUN wget https://github.com/dependency-check/dependency-check-sonar-plugin/releases/download/1.2.6/sonar-dependency-check-plugin-1.2.6.jar -P /opt/sonarqube/extensions/plugins
RUN wget https://github.com/spotbugs/sonar-findbugs/releases/download/3.11.1/sonar-findbugs-plugin-3.11.1.jar -P /opt/sonarqube/extensions/plugins

USER sonarqube
ENTRYPOINT ["/opt/sonarqube/bin/run.sh"]
