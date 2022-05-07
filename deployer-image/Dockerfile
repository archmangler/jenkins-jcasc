FROM jenkins/jenkins:latest
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
COPY casc.yaml /var/jenkins_home/casc.yaml
RUN mkdir -p /var/jenkins_home/.aws/
COPY credentials /var/jenkins_home/.aws/credentials
COPY eqonex-loadengine.groovy /usr/local/eqonex-loadengine.groovy
USER root
RUN apt-get update && apt-get -y install kubernetes-client netcat jq bash curl unzip
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip
RUN ./aws/install
RUN aws --version
USER jenkins
