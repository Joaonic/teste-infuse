FROM maven:3.8.3-openjdk-17 as build

ARG JAR_NAME

WORKDIR /workspace/app

COPY pom.xml .
COPY src src

RUN mvn clean install

RUN java -Djarmode=layertools -jar target/${JAR_NAME}.jar extract --destination target/

FROM maven:3.8.3-openjdk-17

ARG DEPENDENCY=/workspace/app

COPY --from=build ${DEPENDENCY}/target/dependencies/ ./
COPY --from=build ${DEPENDENCY}/target/snapshot-dependencies/ ./
COPY --from=build ${DEPENDENCY}/target/spring-boot-loader/ ./
COPY --from=build ${DEPENDENCY}/target/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]