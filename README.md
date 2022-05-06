# Kubernetes Deployment pipeline for `Eqonex Matching Engine Load Test Workbench`

* The code in this repo demonstrates an automated pipeline to deploy `Eqonex Matching Engine Load Test Workbench` to kubernetes and configure it using the eqonex-loadengine API.
* A jenkins pipeline image is first deployed to kubernetes, it then deploys eqonex-loadengine from a pre-configured image (hosted at dockerhub)
* The pipeline then interacts with the eqonex-loadengine API to create a default user.

# How to deploy (MacOS):

* Install Rancher desktop on your computer to get a convenient Kubernetes environment for development: (https://github.com/rancher-sandbox/rancher-desktop/releases/download/v1.0.0/Rancher.Desktop-1.0.0.x86_64.dmg)
* Clone down the repo: git clone https://github.com/archmangler/jenkins-jcasc
* Run the demo deployer:

```
(base) welcome@Traianos-MacBook-Pro jenkins-jcasc % ./rundemo.sh 
deployment.apps/jenkins unchanged
service/jenkins-service unchanged
rolebinding.rbac.authorization.k8s.io/modify-pods-to-sa unchanged
role.rbac.authorization.k8s.io/modify-pods unchanged
serviceaccount/internal-kubectl unchanged
NAME                       READY   STATUS    RESTARTS   AGE
jenkins-6447464769-h4jpm   1/1     Running   0          2m11s
NAME                       READY   STATUS    RESTARTS   AGE
jenkins-6447464769-h4jpm   1/1     Running   0          2m12s
NAME                       READY   STATUS    RESTARTS   AGE
jenkins-6447464769-h4jpm   1/1     Running   0          2m13s

NAME              TYPE       CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE
jenkins-service   NodePort   10.43.49.147   <none>        8080:30888/TCP   2m15s
```

* Browse to http://127.0.0.1:30888/ address and login with `admin`, `admin`:

![alt text](content/jenkins-login.png?raw=true "Deployer Login") 

* select the single pipeline configured ("eqonex-loadengine") and click "build"

![alt text](content/eqonex-loadengine-pipeline.png?raw=true "Deploy Pipeline")

* Get eqonex-loadengine login password from the Jenkins console output:

![alt text](content/password.png?raw=true "`Eqonex Matching Engine Load Test Workbench` Password")

* Access the `Eqonex Matching Engine Load Test Workbench` UI at `https://127.0.0.1:30743/eqonex-loadengineuser/login` using the credential provided in the pipeline console output (This should be ready in the "Configuring" Jenkins build stage):

![alt text](content/eqonex-loadengine.png?raw=true "`Eqonex Matching Engine Load Test Workbench` UI")

The user configured via the API should be visible in the `users` section:

![alt text](content/eqonex-loadengine-users.png?raw=true "`Eqonex Matching Engine Load Test Workbench` user")

# Extending the eqonex-loadengine deployer pipeline

* The deployer pipeline is defined as a declarative Jenkins pipeline DSL based on groovy and deployed as an immutable docker image.
* To modify and update the pipeline in the docker image, modify `eqonex-loadengine.groovy` in `deployer-image` folder and rebuild the image:

```
#Note: Use your  specific registry tag below ...
cd deployer-image/
docker build -t archbungle/jenkins:jcasc-0.0.x
docker push archbungle/jenkins:jcasc-0.0.x
```

(Modify to your personal image registry requirements)


# Repositories:

* Deployer: https://github.com/archmangler/jenkins-jcasc
 
#  Issues

- n/a

# ToDo (Pending)

- Docker Standalone for ECS (for lighter deploy footprint)
