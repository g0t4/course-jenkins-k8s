pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Started'
                sleep 5
                echo 'Half way'
                sleep 5
                echo 'Ended'
            }
        }
    }
}

