# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.4-17-jdk-alpine AS build

# Set the working directory to /app
WORKDIR /app

# Copy the source code
COPY . .

# Build the application
RUN mvn clean install -DskipTests 

# Create a new image with the JAR file
FROM openjdk:17-jdk-alpine

# Copy the JAR file from the build image
COPY --from=build /app/target/GestionProjet-0.0.1-SNAPSHOT.jar /app/GestionProjet-0.0.1-SNAPSHOT.jar

# Expose port 9090
EXPOSE 9090

# Define environment variable
ENV JAVA_OPTS=""

# Run the Java application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/GestionProjet-0.0.1-SNAPSHOT.jar"]
