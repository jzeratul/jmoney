FROM fabric8/java-alpine-openjdk11-jre
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=*.jar
COPY jmoney-service/target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]