FROM alibabadragonwell/dragonwell:17-anolis

WORKDIR /gateway

ARG JAR_FILE=target/gateway-1.0.0.jar

COPY target/gateway-1.0.0.jar app.jar

CMD ["java", "-jar", "app.jar"]

EXPOSE 7573

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 60; java $JAVA_OPTS -jar app.jar
