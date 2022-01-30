# Kubernetes Deployment pipeline for Covenant

* The code in this repo demonstrates an automated pipeline to deploy Covenant () to kubernetes and configure it using the covenant API.
* A jenkins pipeline image is first deployed to kubernetes, it then deploys covenant from a pre-configured image (hosted at dockerhub)
* The pipeline then interacts with the covenant API to create a default user.

# How to deploy (MacOS):

* Install Rancher desktop on your computer: (https://github.com/rancher-sandbox/rancher-desktop/releases/download/v1.0.0/Rancher.Desktop-1.0.0.x86_64.dmg)
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

* select the single pipeline configured ("covenant") and click "build"

![alt text](content/covenant-pipeline.png?raw=true "Deploy Pipeline")

* Get covenant login password from the Jenkins console output:

![alt text](content/password.png?raw=true "Covenant Password")

* Access the Covenant UI at `https://127.0.0.1:30743/covenantuser/login` using the credential provided in the pipeline console output (This should be ready in the "Configuring" Jenkins build stage):

![alt text](content/covenant.png?raw=true "Covenant UI")

The user configured via the API should be visible in the `users` section:

![alt text](content/covenant-users.png?raw=true "Covenant user")

# Building a new deployer image

The jenkins deployer image can be rebuilt using the sources in `image/`:

* To build an updated image:

```
cd image/
docker build -t archbungle/jenkins:jcasc-0.0.x
docker push archbungle/jenkins:jcasc-0.0.x
```

(Modify to your personal image registry requirements)

* NOTE: The bootstrapped Covenant image is available at Dockerhub: `archbungle/kovenant:0.0.1`

#  Issues

* The covenant API is poorly documented, and swagger is broken: https://github.com/cobbr/Covenant/issues/352

# ToDo (Pending)

- Configure Listener
- Configure Launcher
- Deploy + Configure Grunt
- Test connection between Grunt and Covenant