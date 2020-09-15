FROM openjdk:8u212-jre-slim-stretch
VOLUME /tmp
ARG PROJECT_NAME=""
ADD ${PROJECT_NAME}-api/target/${PROJECT_NAME}-api-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
EXPOSE  8008
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -DJASYPT_ENCRYPTOR_PASSWORD=ZACK_KEY -Dsun.net.httpserver.nodelay=true -jar /app.jar"]
