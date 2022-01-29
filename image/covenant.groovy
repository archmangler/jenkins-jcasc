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
               kubectl get pods -n covenant
               mkdir -p ~/.ssh
               touch ~/.ssh/known_hosts
               rm -rf kovenant
               git clone https://github.com/archmangler/kovenant.git
               cd kovenant
               kubectl apply -f covenant.yaml
               for i in `seq 1 20`;do echo "\$i - waiting for covenant to get ready ..." - \$(sleep 1);done
               '''
            }
        }

       stage("Configuring") {
            steps {
               sh '''
               kubectl get pods -o wide -n covenant
               kubectl get services -n covenant
               nc -zv covenant-service 7443
               COVENANT_IP=\$(kubectl get service covenant-service -n covenant -o json | jq -r '.spec.clusterIP')
               COVENANT_TOKEN=\$(curl -vk -X POST --header 'Content-Type: application/json-patch+json' --header 'Accept: application/json' -d '{ "username":"AdminUser", "password":"Cr4ckM0nk3y!!"}' https://\$COVENANT_IP:7443/api/users/login | jq -r '.covenantToken')
               echo \$COVENANT_TOKEN
               '''
            }
        }

        stage("Decommissioning") {
            steps {
               sh '''
               for i in `seq 1 30`;do echo "\$i - running covenant ..." - \$(sleep 5);done
               echo "shutting down covenant ..."
               cd kovenant
               kubectl delete -f covenant.yaml
               for i in `seq 1 10`;do echo "\$i - waiting for covenant to shut down ..." - \$(sleep 1);done
               kubectl get pods -n covenant
               '''
            }
        }
    }
}
""")
}
}
}
