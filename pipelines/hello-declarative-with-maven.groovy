pipeline {
    agent {
        kubernetes {
yaml '''
spec:
    containers:
    - name: maven
      image: maven
      command:
      - sleep
      args:
      - infinity
'''
        }
    }


    stages {
        stage('Hello') {
            steps {
                container('maven'){
                    sh "mvn -version"
                }
                echo 'Started'
                sleep 5
                echo 'Half way'
                sleep 5
                echo 'Ended'
            }
        }
    }
}
