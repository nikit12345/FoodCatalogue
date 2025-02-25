FROM openjdk:17-alpine
EXPOSE 9093

WORKDIR /opt
COPY target/*jar /opt/foodcatalogue.jar
ENTRYPOINT exec java $JAVA_OPTS -jar foodcatalogue.jar
