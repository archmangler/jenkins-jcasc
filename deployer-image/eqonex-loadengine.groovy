pipelineJob("ragnarok") {
  definition {
    cps {
         sandbox(true)
script("""
pipeline {
    agent any

    parameters {
      string(name: 'MFA_TOKEN', defaultValue: 'null', description: 'Enter MFA Token')
    }
    
    stages {
        stage("Testing") {
            steps {
                sh '''#!/bin/bash
                set +x
                git clone "https://traianowelcome:REDACTED@bitbucket.org/traianowelcome/trading-system-load-engine.git"
                cd trading-system-load-engine/
                source env.sh \${MFA_TOKEN}
                pwd
                ls -l
                echo \$HOME
                cd  ~/
                pwd
                '''
            }
        }

        stage("Deploying") {
            steps {
               sh '''#!/bin/bash
               for i in `seq 1 5`;do echo "\$i - waiting to get ready ..." - \$(sleep 1);done
               '''
            }
        }

       stage("Configuring") {
            steps {
               sh '''#!/bin/bash
               kubectl get pods -o wide -n ragnarok
               kubectl get services -n ragnarok
               for i in `seq 1 5`;do echo "\$i - waiting to get ready ..." - \$(sleep 1);done
               '''
            }
        }

        stage("Decommissioning") {
            steps {
               sh '''#!/bin/bash
               for i in `seq 1 5`;do echo "\$i - running stuff ..." - \$(sleep 5);done
               for i in `seq 1 5`;do echo "\$i - waiting for shut down ..." - \$(sleep 1);done
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
