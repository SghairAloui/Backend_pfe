# Use official OpenJDK base image with Java 17
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/GestionProjet-0.0.1-SNAPSHOT.jar /app/GestionProjet-0.0.1-SNAPSHOT.jar

# Expose the port that the application runs on
EXPOSE 9090

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "GestionProjet-0.0.1-SNAPSHOT.jar"]
