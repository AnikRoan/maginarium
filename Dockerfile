FROM maven:3.9.5-eclipse-temurin-21 AS builder
# Creating directoiry at the app-image
WORKDIR /aimaginarium_be
# Copying files to the directory at the app-image
COPY . /aimaginarium_be/.
# Running Maven to biuld the project
RUN mvn -f /aimaginarium_be/pom.xml clean install -Dmaven.test.skip=false

FROM eclipse-temurin:21-jre
WORKDIR /aimaginarium_be
COPY --from=builder /aimaginarium_be/target/*.jar /aimaginarium_be/*.jar
# App container port
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/aimaginarium_be/*.jar"]