FROM adoptopenjdk/maven-openjdk11 as builder
WORKDIR /application

# COPY ./app/target ./app/target

COPY app ./app
COPY client ./client
COPY order ./order
COPY persistence ./persistence
COPY product ./product
COPY coverage ./coverage
COPY pom.xml ./
RUN mvn clean package -DskipTests=true

RUN java -Djarmode=layertools -jar app/target/app-1.0-SNAPSHOT.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
EXPOSE 8090
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]