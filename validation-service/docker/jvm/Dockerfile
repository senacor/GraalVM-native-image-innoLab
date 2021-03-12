FROM adoptopenjdk/maven-openjdk11 AS MAVEN_BUILD
RUN mkdir -p .maven-m2
COPY .maven-m2 /build/.maven-m2
COPY src /build/src
COPY target /build/target
COPY build.sh pom.xml /build/
WORKDIR /build
RUN ./build.sh


FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/validation-service-0.0.1-SNAPSHOT.jar /app/validation-service.jar
CMD [ "java", "-jar", "validation-service.jar" ]