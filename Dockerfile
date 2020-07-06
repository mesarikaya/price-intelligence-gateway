FROM adoptopenjdk/openjdk14:ubi
LABEL PROJECT_NAME="priceintelligencegateway"
WORKDIR /opt
ENV PORT 9090
EXPOSE 9090
COPY target/*.jar /opt/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]