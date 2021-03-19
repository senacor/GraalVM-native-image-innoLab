FROM gradle:jdk11 AS BUILD
COPY src /build/src
COPY build.gradle gradle.properties micronaut-cli.yml settings.gradle /build/
WORKDIR /build
RUN gradle --no-daemon clean assemble test

FROM ghcr.io/graalvm/graalvm-ce:java11-21.0.0 AS GRAALVM
RUN gu install native-image
WORKDIR /home/app
COPY --from=BUILD /build/build/layers/libs /home/app/libs
COPY --from=BUILD /build/build/layers/resources /home/app/resources
COPY --from=BUILD /build/build/layers/application.jar /home/app/application.jar
RUN native-image -H:Class=com.senacor.innolab.graalvm.credit.details.Application -H:Name=application --no-fallback -cp /home/app/libs/*.jar:/home/app/resources:/home/app/application.jar -H:+StaticExecutableWithDynamicLibC

FROM gcr.io/distroless/cc-debian10
COPY --from=GRAALVM /home/app/application /app/application
ENTRYPOINT ["/app/application"]