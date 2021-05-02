pipeline {
    agent any
    
    stages {
        stage('Preparation') {
            steps {
                git branch: 'jenkins', url: 'https://github.com/injcristianrojas/swsec-intro.git'
            }
        }
        stage('Webapp build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Spotbugs (SAST)') {
            steps {
                sh 'mvn spotbugs:spotbugs'
            }
        }
        stage('OWASP Dependency Check (SCA)') {
            steps {
                sh 'mvn -Dformat=XML org.owasp:dependency-check-maven:check'
            }
        }
        stage('OWASP ZAP proxy analyzer launch') {
            steps {
                sh '/opt/zaproxy/zap.sh -daemon -port 8989 -host 0.0.0.0 -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true -config api.disablekey=true -config scanner.strength=INSANE &'
            }
        }
        stage('Webapp server launch') {
            steps {
                sh 'mvn jetty:run-forked'
            }
        }
        stage('OWASP ZAP (DAST)') {
            steps {
                sh 'mvn de.martinreinhardt-online:zap-maven-plugin:analyze'
            }
        }
    }
    
    post {
        always {
            sh 'mvn jetty:stop'
            sh 'curl http://localhost:8989/JSON/core/action/shutdown/'
        }
        success {
            script {
                dependencyCheckPublisher()
                recordIssues enabledForFailure: true, tool: spotBugs(pattern: 'target/spotbugsXml.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
                publishHTML reportDir: 'target/zap-reports', reportFiles: 'zapReport.html', reportName: 'OWASP ZAP Report', reportTitles: 'OWASP ZAP Report'
            }
        }
    }
}