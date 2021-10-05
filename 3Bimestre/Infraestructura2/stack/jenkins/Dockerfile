FROM docker.io/jenkins/jenkins:2.307-jdk11
USER root

ENV JENKINS_UC_DOWNLOAD http://mirrors.jenkins-ci.org
#ENV JENKINS_UC_DOWNLOAD 'http://ftp-nyc.osuosl.org/pub/jenkins'
ENV CURL_OPTIONS -sSfLk -C -

RUN apt-get update && apt-get install -y tree nano curl sudo ; \
    curl https://get.docker.com/builds/Linux/x86_64/docker-latest.tgz | tar xvz -C /tmp/ && mv /tmp/docker/docker /usr/bin/docker ; \
    curl -L "https://github.com/docker/compose/releases/download/1.23.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose ; \
    chmod 755 /usr/local/bin/docker-compose ; \
    apt-get clean --dry-run; \
    usermod -a -G sudo jenkins ; \
    echo "jenkins ALL=(ALL:ALL) NOPASSWD:ALL" >> /etc/sudoers ; \
    mkdir /app /server;

USER jenkins

ENV CASC_JENKINS_CONFIG="/tmp/jenkins.yml"
COPY jenkins.yml ${CASC_JENKINS_CONFIG}
COPY app.jar /app/app.jar
COPY ./server /server
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

