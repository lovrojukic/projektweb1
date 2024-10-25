# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR to the container
COPY target/jedan-0.0.1-SNAPSHOT.jar /app/jedan-0.0.1-SNAPSHOT.jar

# Command to run the app
CMD ["java", "-jar", "jedan-0.0.1-SNAPSHOT.jar"]

# Expose port 8080 to the outside world
EXPOSE 8080
