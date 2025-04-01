
FROM eclipse-temurin:21-jdk AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and source files
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the application and skip tests
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jdk AS runtime

# Set working directory
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port for external access if needed (e.g., REST API)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
