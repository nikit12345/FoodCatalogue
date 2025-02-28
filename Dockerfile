FROM openjdk:17-alpine
EXPOSE 9091

WORKDIR /opt
COPY target/*jar /opt/userinfo.jar
ENTRYPOINT exec java $JAVA_OPTS -jar userinfo.jar
