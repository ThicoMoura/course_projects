FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN ./gradlew build -x test

FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S demo && adduser -S demo -G demo
VOLUME /tmp
USER demo
COPY --from=build /workspace/app/build/libs/credit-application-system-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","credit-application-system-0.0.1-SNAPSHOT.jar"]