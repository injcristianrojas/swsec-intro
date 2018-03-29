FROM sonarqube:7.0

RUN wget https://github.com/stevespringett/dependency-check-sonar-plugin/releases/download/1.1.0/sonar-dependency-check-plugin-1.1.0.jar -P $SONARQUBE_HOME/extensions/plugins
RUN wget https://github.com/spotbugs/sonar-findbugs/releases/download/3.7.0/sonar-findbugs-plugin-3.7.0.jar -P $SONARQUBE_HOME/extensions/plugins
RUN wget https://sonarsource.bintray.com/Distribution/sonar-java-plugin/sonar-java-plugin-5.2.0.13398.jar -P $SONARQUBE_HOME/extensions/plugins
