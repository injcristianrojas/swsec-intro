FROM sonarqube:lts

USER root
RUN apt-get update
RUN apt-get install -y wget
RUN rm -rf /var/lib/apt/lists/*
RUN wget https://github.com/dependency-check/dependency-check-sonar-plugin/releases/download/2.0.5/sonar-dependency-check-plugin-2.0.5.jar -P /opt/sonarqube/extensions/plugins
RUN wget https://github.com/spotbugs/sonar-findbugs/releases/download/4.0.0/sonar-findbugs-plugin-4.0.0.jar -P /opt/sonarqube/extensions/plugins

USER sonarqube
ENTRYPOINT ["/opt/sonarqube/bin/run.sh"]
