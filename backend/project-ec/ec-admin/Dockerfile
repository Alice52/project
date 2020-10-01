FROM openjdk:8u212-jre-slim-stretch
VOLUME /tmp
ARG MODULE_NAME="ec-admin"
ADD build/libs/${MODULE_NAME}-1.0-SNAPSHOT-original.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -DJASYPT_ENCRYPTOR_PASSWORD=ZACK_KEY -Dsun.net.httpserver.nodelay=true -jar /app.jar"]
