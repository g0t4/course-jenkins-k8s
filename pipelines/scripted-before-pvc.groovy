podTemplate(yaml: '''
spec:
    containers:
    - name: jdk
      image: eclipse-temurin:21
      command:
      - sleep
      args:
      - infinity
'''){
    node(POD_LABEL){
        stage('checkout'){
            sh "pwd && ls -al"
            git "https://github.com/g0t4/course-jenkins-k8s-spc"
            sh "ls -al"
        }
        stage('package') {
            container('jdk'){
                sh "ls -al"
                sh "./mvnw package --batch-mode --no-transfer-progress"
                //sleep 1000
            }
        }
        stage('capture'){
            archiveArtifacts '**/target/*.jar'
            junit '**/target/surefire-reports/TEST*.xml'
            recordCoverage tools: [[parser: 'JACOCO']]
        }
    }
}


