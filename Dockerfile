# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application files
COPY . .

# Grant permission to Gradle wrapper and build files
RUN chmod +x ./gradlew
RUN chmod +x ./gradlew.bat

# Build the application
RUN ./gradlew build

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["./gradlew", "bootRun"]
