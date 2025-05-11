# Use OpenJDK 11 for Railway deployment
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy all files from the current directory to /app inside the container
COPY . /app

# Install Gradle 8.1.1 (compatible with Java 11)
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.1.1-bin.zip && \
    unzip gradle-8.1.1-bin.zip && \
    mv gradle-8.1.1 /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Set Gradle Home
ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:/opt/gradle/bin

# Verify installations
RUN gradle -v && java -version

# Build the project
RUN gradle clean build

# Expose the application port
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["gradle", "bootRun"]
