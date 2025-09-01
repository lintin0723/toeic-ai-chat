# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create the final, lean image
FROM eclipse-temurin:17-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
