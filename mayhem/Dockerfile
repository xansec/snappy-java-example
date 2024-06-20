FROM ubuntu AS builder

ENV GRADLE_VERSION=8.4

RUN apt update -y && DEBIAN_FRONTEND=noninteractive apt install -y zip unzip openjdk-17-jdk wget 
RUN wget https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip
RUN mkdir /opt/gradle
RUN unzip -d /opt/gradle gradle-$GRADLE_VERSION-bin.zip
ENV PATH=/opt/gradle/gradle-$GRADLE_VERSION/bin:$PATH
COPY . /snappyfuzz
WORKDIR /snappyfuzz
RUN gradle shadowJar

FROM cifuzz/jazzer:latest

COPY --from=builder /snappyfuzz/targets/build/libs/targets-all.jar /snappy-fuzz.jar
COPY ./dicts/jpeg.dict /dictionaries/jpeg.dict
