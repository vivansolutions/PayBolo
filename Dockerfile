
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application files
COPY . /app

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["./gradlew", "bootRun"]
