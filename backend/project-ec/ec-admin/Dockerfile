FROM openjdk:8u121-jre-alpine
VOLUME /tmp
ARG MODULE_NAME="ec-admin"
ARG JASYPT_ENCRYPTOR_PASSWORD=""
ENV JAVA_OPTS=""

COPY build/libs/${MODULE_NAME}-1.0-SNAPSHOT-original.jar app.jar
#RUN sh -c 'touch /app.jar'

EXPOSE 9001
# Build a shell script because the ENTRYPOINT command doesn't like using ENV
RUN echo -e "#!/bin/sh\njava ${JAVA_OPTS} -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -DJASYPT_ENCRYPTOR_PASSWORD=${JASYPT_ENCRYPTOR_PASSWORD} -Dsun.net.httpserver.nodelay=true -jar /app.jar" > ./entrypoint.sh
RUN chmod +x /entrypoint.sh
RUN echo $PWD
RUN cat ./entrypoint.sh

RUN ls -la
ENTRYPOINT ["/entrypoint.sh"]
