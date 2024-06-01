FROM alibabadragonwell/dragonwell:17-anolis

WORKDIR /user-service

ARG JAR_FILE=target/user-service-1.0.0.jar

COPY target/user-service-1.0.0.jar app.jar

CMD ["java", "-jar", "app.jar"]

EXPOSE 4001

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 60; java $JAVA_OPTS -jar app.jar
