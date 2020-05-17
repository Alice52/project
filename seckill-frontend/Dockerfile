#########################
# version 1
#########################

# FROM java:8
# ENV MYPATH /opt/cloud
# WORKDIR $MYPATH
# VOLUME /tmp
# CMD ["echo",  "$PWD"]
# ADD cloud-provider-payment8001/**/*.jar $MYPATH
# EXPOSE  8010
# ENTRYPOINT ["java", "-jar","$MYPATH/*.jar"]


#########################
# version 2
#########################
# 选择JDK
FROM openjdk:8u212-jre-slim-stretch
VOLUME /tmp
ADD target/samples-order-1.0.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
# 命令行
# 构建 docker build -t samples-order:1.0.0 .
# 测试运行 docker run --net=host samples-order:1.0.0 /bin/bash
# 进入bash docker exec -it [container_id] bash
# 后台运行(开发) docker run -d --net=host samples-order:1.0.0
