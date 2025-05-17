# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Set environment paths for Gradle (optional but useful)
ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:/opt/gradle/bin

# Set working directory
WORKDIR /app

# Copy everything into container
COPY . .

# Verify Java & Gradle versions
RUN ./gradlew -v && java -version

# Clear Gradle cache (optional cleanup)
RUN rm -rf /root/.gradle/caches/
RUN mkdir -p /root/.gradle && chmod -R 777 /root/.gradle

# Make Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the project
RUN ./gradlew clean build --no-daemon --stacktrace

# (Optional) Expose port if your app is a server (Not needed for Android build)
# EXPOSE 8080

# Default ENTRYPOINT (optional — if your project needs bootRun or jar run)
# ENTRYPOINT ["./gradlew", "bootRun"]