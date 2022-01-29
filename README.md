# Jenkins deployment for kubernetes with full configuration as code (CASC)

- Jenkins CASC built into a deployable image

# Building a new image

* To build an updated image:

```
cd image/
docker build -t archbungle/jenkins:jcasc-0.0.x
docker push archbungle/jenkins:jcasc-0.0.x
```

(Modify to your personal requirements)
