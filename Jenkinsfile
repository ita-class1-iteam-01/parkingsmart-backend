pipeline {
  agent { label 'master' }
  environment {
    JENKINS_NODE_COOKIE = "donotkillme"
  }
  stages {
    stage('clone'){
      steps{
        echo 'clone begin, clone the code from dev branch'
        git branch:'dev',url:'https://github.com/carrymaniac/parkingsmart-backend'
        echo 'clone end'
      }
    }
    stage('test') {
      steps {
        echo 'test begin'
        bat './gradlew test'
        echo 'test end'
      }
    }
    stage('build') {
      steps {
        echo 'build begin'
        bat './gradlew build'
        echo 'build end'
      }
    }
    stage('deploy') {
      steps {
        echo 'deploy'
        bat "run.bat"
        bat 'start /b java -jar build\\libs\\parkingsmart-backend-0.0.1-SNAPSHOT.jar'
      }
    }
  }
}