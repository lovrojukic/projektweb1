# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper files and pom.xml
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

# Run Maven to download dependencies
RUN ./mvnw dependency:resolve

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean install -DskipTests

# Copy the generated JAR file to the container
COPY target/jedan-0.0.1-SNAPSHOT.jar /app/jedan-0.0.1-SNAPSHOT.jar

# Command to run the app
CMD ["java", "-jar", "/app/jedan-0.0.1-SNAPSHOT.jar"]

# Expose port 8080 to the outside world
EXPOSE 8080
