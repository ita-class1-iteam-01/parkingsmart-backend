pipeline {
  agent { label 'master' }
  environment {
    JENKINS_NODE_COOKIE = "donotkillme"
  }
  stages {
    stage('test') {
      steps {
        echo 'test'
        bat './gradlew test'
      }
    }
    stage('build') {
      steps {
        echo 'build'
        bat './gradlew build'
      }
    }
    stage('deploy') {
      steps {
        echo 'deploy'
        bat "run.bat"
        bat 'start /b java -jar build\\libs\\todo-0.0.1-SNAPSHOT.jar'
      }
    }
  }
}