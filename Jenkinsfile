#!/usr/bin/env groovy

node {

  stage('Checkout') {
    checkout scm
  }

  stage('Build') {
    docker.image('zenoss/build-tools:0.0.10').inside() { 
      withMaven(mavenSettingsConfig: 'bintray') {
        sh '''
          make -C python build
          export PATH=$MVN_CMD_DIR:$PATH;
          mvn -B -f java package
        '''
      }
    }
  }

  stage('Publish') {
    def remote = [:]
    withFolderProperties {
      withCredentials( [sshUserPrivateKey(credentialsId: 'PUBLISH_SSH_KEY', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')] ) {
        remote.name = env.PUBLISH_SSH_HOST
        remote.host = env.PUBLISH_SSH_HOST
        remote.user = userName
        remote.identityFile = identity
        remote.allowAnyHosts = true

        def tar_ver = sh( returnStdout: true, script: '''  awk -F'"' '/version =/{print $2}' python/setup.py ''' ).trim()
        sshPut remote: remote, from: 'python/dist/zenoss.protocols-' + tar_ver + '-py2-none-any.whl', into: env.PUBLISH_SSH_DIR
      }
    }
    docker.image('zenoss/build-tools:0.0.10').inside() { 
      withMaven(mavenSettingsConfig: 'bintray') {
        sh '''
          export PATH=$MVN_CMD_DIR:$PATH;
          mvn -B -f java deploy
        '''
      }
    }
  }

}
