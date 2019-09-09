pipeline {
    agent any
    
    stages {
        stage('Preparation') { // for display purposes
            steps {
                git branch: 'jenkins', url: 'https://github.com/injcristianrojas/swsec-intro.git'
            }
        }
        stage('Compilation, analysis and server startup') {
            steps {
                sh 'mvn compile spotbugs:spotbugs -Dformat=XML org.owasp:dependency-check-maven:check jetty:run-forked'
            }
        }
        stage('ZAP startup') {
            steps {
                script {
                    startZap(host: "127.0.0.1", port: 9090, timeout:500, zapHome: "/opt/zaproxy")
                }
            }
        }
        stage('ZAP Attack') {
            steps {
                sh 'mvn -DzapPath=/opt/zaproxy de.martinreinhardt-online:zap-maven-plugin:analyze'
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
                archiveZap()
                recordIssues enabledForFailure: true, tool: spotBugs(pattern: 'target/spotbugsXml.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            }
        }
    }
}