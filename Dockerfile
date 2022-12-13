# AS <NAME> to name this stage as maven
FROM maven:3.8.5 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn clean install

# For Java 17,
FROM openjdk:17-jdk-slim

ARG JAR_FILE=countries.jar

WORKDIR /opt/app

# Copy the countries.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","countries.jar"]