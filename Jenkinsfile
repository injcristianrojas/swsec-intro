pipeline {
    agent any

    tools {
        maven 'M3'
    }
    
    stages {
        stage('Preparation') { // for display purposes
            steps {
                git branch: 'jenkins', url: 'https://github.com/injcristianrojas/swsec-intro.git'
            }
        }
        stage('Server startup') {
            steps {
                sh 'mvn compile jetty:run-forked'
            }
        }
        stage('ZAP startup') {
            steps {
                script {
                    startZap(host: "127.0.0.1", port: 9090, timeout:500, zapHome: "/opt/zaproxy")
                    importZapScanPolicy(policyPath: "${env.WORKSPACE}/scan.policy")
                }
            }
        }
        stage('Crawl') {
            steps {
                script {
                    sh "mvn verify -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=9090" // Proxy tests through ZAP
                }
            }
        }
        stage('ZAP Attack') {
            steps {
                script {
                    runZapCrawler(host: "http://127.0.0.1:8090")
                    runZapAttack(scanPolicyName: "scan")
                }
            }
        }
    }
    
    post {
        always {
            sh 'mvn jetty:stop'
        }
        success {
            script {
                archiveZap()
            }
        }
    }
}