FROM openjdk:8u121-jre-alpine
VOLUME /tmp
ARG MODULE_NAME="ec-thirdparty"
ARG JASYPT_ENCRYPTOR_PASSWORD="ZACK_KEY"
ADD build/libs/${MODULE_NAME}-1.0-SNAPSHOT-original.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

EXPOSE 8010
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -DJASYPT_ENCRYPTOR_PASSWORD=ZACK_KEY -Dsun.net.httpserver.nodelay=true -jar /app.jar"]
