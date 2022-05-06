pipelineJob("ragnarok") {
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
               for i in `seq 1 20`;do echo "\$i - waiting to get ready ..." - \$(sleep 1);done
               '''
            }
        }

       stage("Configuring") {
            steps {
               sh '''
               kubectl get pods -o wide -n ragnarok
               kubectl get services -n ragnarok
               for i in `seq 1 20`;do echo "\$i - waiting to get ready ..." - \$(sleep 1);done
               '''
            }
        }

        stage("Decommissioning") {
            steps {
               sh '''
               for i in `seq 1 30`;do echo "\$i - running stuff ..." - \$(sleep 5);done
               for i in `seq 1 10`;do echo "\$i - waiting for shut down ..." - \$(sleep 1);done
               kubectl get pods -n ragnarok
               '''
            }
        }
    }
}
""")
}
}
}
