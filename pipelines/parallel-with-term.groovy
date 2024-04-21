/* groovylint-disable DuplicateStringLiteral */
podTemplate(
  name: 'build',
  yaml:'''
spec:
    terminationGracePeriodSeconds: 2
''',
  containers: [ containerTemplate(name: 'jdk', image: 'eclipse-temurin', ttyEnabled: true, command: 'cat') ]
) {
  node(POD_LABEL) {
    stage('build-it') {
      echo 'building...'
      sh 'ls -al'
      sh 'echo foo > bar'
      container('jdk') {
        sh 'java -version'
      }

      podTemplate(name: 'nested') {
        node(POD_LABEL) {
          echo 'nesting...'
          sh 'ls -al'
          sleep 3
        }
      }

      sh 'ls -al'
      sleep 3
    }
  }
}

parallel(

  testsA: {
    podTemplate(name: 'tests-A') {
      node(POD_LABEL) {
        echo 'testing A...'
        sh 'ls -al'
        sleep 5
      }
    }
  },

  testsB: {
    podTemplate(name: 'tests-B') {
      node(POD_LABEL) {
        echo 'testing B...'
        sleep 10
      }
    }
  },

  failFast: true

)

podTemplate(name: 'capture') {
  node(POD_LABEL) {
    stage('capture-it') {
      echo 'capturing...'
      sleep 5
    }
  }
}
