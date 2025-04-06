FROM eclipse-temurin:24-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/mockalender-*.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the application with the development profile
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=development"]
