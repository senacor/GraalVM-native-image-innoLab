FROM gradle:jdk11 AS BUILD
COPY src /build/src
COPY build.gradle gradle.properties micronaut-cli.yml settings.gradle /build/
WORKDIR /build
RUN gradle --no-daemon clean assemble test

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=BUILD /build/build/libs/credit-details-service-0.1-all.jar /app/credit-details-service.jar
CMD [ "java", "-jar", "credit-details-service.jar" ]