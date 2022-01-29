pipelineJob("covenant") {
  definition {
    cps {
         sandbox(true)
script("""
pipeline {
    agent any
    stages {
        stage("Testing") {
            steps {
               sh '''
               echo `env`
               kubectl version --client=true
               '''  
            }
        }

        stage("Deploying") {
            steps {
               sh '''
               kubectl version --client=true
               kubectl get pods -n covenant
               '''
            }
        }

        stage("Decommissioning") {
            steps {
               sh '''
               kubectl version --client=true
               '''
            }
        }
    }
}
""")
}
}
}
