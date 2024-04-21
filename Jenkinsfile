podTemplate(name: 'build') {
  node(POD_LABEL) {
    stage('build') {
      sh 'echo build'
      sh 'ls -al'
      sh 'echo foo > bar'

      podTemplate(name: 'nested') {
        node(POD_LABEL) {
          sh 'echo nested'
          sh 'ls -al'
          sleep 3
        }
      }

      sleep 3
    }
  }
}

parallel(

  testsA: {
    podTemplate(name: 'tests-A') {
      node(POD_LABEL) {
        sh 'echo tests A'
        sleep 5
      }
    }
  },

  testsB: {
    podTemplate(name: 'tests-B') {
      node(POD_LABEL) {
        sh 'echo tests B'
        sleep 10
      }
    }
  },

  failFast: true

)

podTemplate(name: 'capture') {
  node(POD_LABEL) {
    stage('capture') {
      sh 'echo capture'
      sleep 5
    }
  }
}
