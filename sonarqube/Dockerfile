FROM sonarqube:7.0

RUN rm $SONARQUBE_HOME/extensions/plugins/*

RUN apt-get -y update
RUN apt-get -y install git maven

RUN rm -rf /tmp/zap-sonar
RUN git clone https://github.com/Coveros/zap-sonar-plugin.git /tmp/zap-sonar
RUN cd /tmp/zap-sonar;mvn clean package
RUN mv /tmp/zap-sonar/sonar-zap-plugin/target/sonar-zap-plugin-1.0.2-SNAPSHOT.jar $SONARQUBE_HOME/extensions/plugins

RUN wget https://github.com/stevespringett/dependency-check-sonar-plugin/releases/download/1.1.0/sonar-dependency-check-plugin-1.1.0.jar -P $SONARQUBE_HOME/extensions/plugins
RUN wget https://github.com/spotbugs/sonar-findbugs/releases/download/3.7.0/sonar-findbugs-plugin-3.7.0.jar -P $SONARQUBE_HOME/extensions/plugins
RUN wget https://sonarsource.bintray.com/Distribution/sonar-java-plugin/sonar-java-plugin-5.2.0.13398.jar -P $SONARQUBE_HOME/extensions/plugins
