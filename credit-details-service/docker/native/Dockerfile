FROM ghcr.io/graalvm/graalvm-ce:java11-21.0.0 AS GRAALVM
RUN gu install native-image

WORKDIR /build
COPY src /build/src
COPY .gradle /build/.gradle
COPY gradle /build/gradle
COPY build.gradle gradle.properties gradlew micronaut-cli.yml settings.gradle /build/

RUN ./gradlew --no-daemon clean assemble test

WORKDIR /home/app
RUN cp -r /build/build/layers/libs /home/app/libs
RUN cp -r /build/build/layers/resources /home/app/resources
RUN cp /build/build/layers/application.jar /home/app/application.jar

RUN native-image -H:Class=com.senacor.innolab.graalvm.credit.details.Application -H:Name=application --no-fallback -cp /home/app/libs/*.jar:/home/app/resources:/home/app/application.jar -H:+StaticExecutableWithDynamicLibC

FROM gcr.io/distroless/cc-debian10
COPY --from=GRAALVM /home/app/application /app/application
ENTRYPOINT ["/app/application"]