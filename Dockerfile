# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file to the container
COPY target/weather-system-0.0.1-SNAPSHOT.jar /app/weather-system.jar

# Expose port 8080 to the Docker host
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "weather-system.jar", "--server.port=8080"]
