# Use an OpenJDK image
FROM openjdk:23-jdk-slim

# Set working directory in the container
WORKDIR /springapp

# Copy the JAR file into the container
COPY build/libs/*.jar springapp.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "springapp.jar"]
