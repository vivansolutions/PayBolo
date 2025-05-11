# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application files
COPY . .

# Grant permission to Gradle wrapper
RUN chmod +x ./gradlew

# Update Gradle and build
RUN ./gradlew clean build --no-daemon --stacktrace

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["./gradlew", "bootRun"]