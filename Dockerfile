
# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application files
COPY . .

# Give permissions to Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew clean build --no-daemon

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["./gradlew", "bootRun"]