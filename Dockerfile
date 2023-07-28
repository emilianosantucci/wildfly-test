FROM quay.io/wildfly/wildfly:27.0.1.Final-jdk17
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

WORKDIR /opt/teleforum
COPY modules ./modules

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
