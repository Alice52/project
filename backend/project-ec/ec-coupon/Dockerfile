FROM openjdk:8u121-jre-alpine
VOLUME /tmp
ARG MODULE_NAME="ec-coupon"
ARG JASYPT_ENCRYPTOR_PASSWORD=""
ENV JAVA_OPTS=""

COPY build/libs/${MODULE_NAME}-1.0-SNAPSHOT-original.jar app.jar

EXPOSE 8020
RUN echo -e "#!/bin/sh\njava ${JAVA_OPTS} -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -DJASYPT_ENCRYPTOR_PASSWORD=${JASYPT_ENCRYPTOR_PASSWORD} -Dsun.net.httpserver.nodelay=true -jar /app.jar" > ./entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
