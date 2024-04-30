# Use official OpenJDK base image with Java 17
FROM openjdk:17-jdk-alpine as builder

# Use official Maven Docker image
FROM maven:3.8.4-openjdk-17 AS maven

# Set the working directory in the Maven container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Add any additional Maven settings or configurations here, if needed

# Build project skipping tests
RUN mvn clean install -DskipTests

# Switch back to the OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the final container
WORKDIR /app

# Copy the packaged jar file from the Maven container to the final container
COPY --from=maven /app/target/GestionProjet-0.0.1-SNAPSHOT.jar /app/GestionProjet-0.0.1-SNAPSHOT.jar

# Expose the port that the application runs on
EXPOSE 9090

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "GestionProjet-0.0.1-SNAPSHOT.jar"]
