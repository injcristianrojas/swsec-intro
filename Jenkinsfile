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
        }
        success {
            script {
                dependencyCheckPublisher()
                recordIssues enabledForFailure: true, tool: spotBugs(pattern: 'target/spotbugsXml.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
                publishHTML reportDir: 'zapreports', reportFiles: 'zapReport.html', reportName: 'OWASP ZAP Report', reportTitles: 'OWASP ZAP Report'
            }
        }
    }
}