FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine AS jre-build
RUN $JAVA_HOME/bin/jlink \
  --add-modules jdk.unsupported,java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.crypto.ec \
  --strip-debug \
  --no-man-pages \
  --no-header-files \
  --compress zip-2 \
  --output /javaruntime

FROM alpine:3.19 AS runner

RUN apk add --no-cache ca-certificates && \
    addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=jre-build /javaruntime $JAVA_HOME

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]