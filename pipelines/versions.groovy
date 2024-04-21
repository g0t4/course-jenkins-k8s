/* groovylint-disable DuplicateStringLiteral */
podTemplate(yaml: '''
    apiVersion: v1
    kind: Pod
    spec:
      containers:
        - name: mvn
          image: maven
          command:
            - sleep
          args:
            - infinity
        - name: mvn3-8
          image: maven:3.8
          command:
            - sleep
          args:
            - infinity
        - name: git
          image: bitnami/git
          command:
          - cat
          tty: true
          # cat + tty in YAML is alternative to "sleep infinity"
    ''',
        containers: [
            // groovy DSL containerTemplate (vs yaml pod spec above)
            containerTemplate(name: 'node', image: 'node', command: 'sleep', args: 'infinity'),
            containerTemplate(name: 'python', image: 'python', command: 'sleep', args: 'infinity'),
            containerTemplate(name: 'dotnet', image: 'mcr.microsoft.com/dotnet/sdk', command: 'cat', ttyEnabled: true)
        ]
    ) {
    node(POD_LABEL) {

        git "https://github.com/g0t4/course-jenkins-k8s" // checkout scm // see repo files foo/bar/Jenkinsfile => shared workspace

        stage('build-info') {
            container('git') {
                sh 'git --version'
                sh 'pwd'
                sh 'ls'
                sh 'echo 12.4 > buildinfo.txt'
                // sh 'git log -1 --format="%H %an %ad" > buildinfo.txt'
            }
        }
        stage('maven tools') {
            container('mvn') {
                echo POD_CONTAINER
                sh 'mvn -version'
                // FYI if issues w/ container, test it manually w/o re-running pipeline:
                //   docker container run --rm -it maven:3.8 bash
                //   kubectl run --rm -i -t --image maven:3.8 tmp -- bash
                // OR, exec into actual pod
            }
            container('mvn3-8') { // dashes in name
                echo POD_CONTAINER
                sh 'mvn -version'
            }
        }
        stage('other') {
            parallel(
                'node': {
                    container('node') {
                        sh 'node --version'
                    }
                },
                'python': {
                    container('python') {
                        sh 'python --version'
                        sh 'pip --version'
                    }
                },
                'dotnet': {
                    container('dotnet') {
                        sh 'dotnet --version'
                        sh 'dotnet --list-runtimes'
                        sh 'pwd'
                        sh 'ls'
                        sh 'cat buildinfo.txt'
                    }
                }
            )
        }
    }
}
