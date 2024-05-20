FROM gradle:8-jdk17 as build
WORKDIR /build
COPY src src
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
ARG GRADLE_PROFILE
RUN gradle clean build

FROM bellsoft/liberica-openjdk-debian:17
VOLUME /tmp
ARG JAR_FILE=method-execution-0.0.1-SNAPSHOT.jar
WORKDIR /application
COPY --from=build /build/build/libs/${JAR_FILE} application.jar

ENTRYPOINT exec java ${JAVA_OPTS} -jar application.jar ${0} ${@}