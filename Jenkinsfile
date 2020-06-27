pipeline{
    agent {
        kubernetes{
            label 'ibar-payday-bank'
            idleMinutes 5
            yamlFile 'jenkins-agents/ibar-payday-bank/jenkins-agent.yaml'
            defaultContainer 'ibar-payday-bank'
        }
    }
    stages{
        stage('Build'){
            steps{
                    sh "mkdir -p /tmp"
                    sh "./gradlew build "
            }
        }
    }
}