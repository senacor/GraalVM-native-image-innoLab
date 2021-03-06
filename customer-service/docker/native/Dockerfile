# See https://github.com/loicmathieu/docker-maven-graalvm/blob/master/Dockerfile
FROM springci/graalvm-ce:21.0-dev-java11 AS MAVEN_BUILD

RUN apt-get update && apt install curl -y

# mvn
ARG MAVEN_VERSION=3.6.3
ARG USER_HOME_DIR="/user"
ARG SHA=c35a1803a6e70a126e80b2b3ae33eed961f83ed74d18fcd16909b2d44d7dada3203f1ffe726c17ef8dcca2dcaa9fca676987befeadc9b9f759967a8cb77181c0
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
# end

RUN mkdir -p .maven-m2

COPY .maven-m2 /build/.maven-m2
COPY src /build/src
COPY target /build/target
COPY build-native.sh pom.xml /build/

WORKDIR /build

RUN ./build-native.sh


FROM oraclelinux:8-slim
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/customer-service /app/customer-service
CMD [ "./customer-service" ]