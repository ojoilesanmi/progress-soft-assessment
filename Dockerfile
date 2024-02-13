
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /workspace/app

COPY pom.xml .

COPY src src

RUN mvn clean package -DskipTests

FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app

COPY --from=build /workspace/app/target/assessment-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "assessment-0.0.1-SNAPSHOT.jar"]
