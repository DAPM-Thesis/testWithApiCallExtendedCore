FROM eclipse-temurin:21-jdk AS build

# Set working directory
WORKDIR /app

# Copy project files (including Maven wrapper)
COPY . .

# Make Maven wrapper executable (required in Linux)
RUN chmod +x mvnw

# Build the Spring Boot application (skip tests)
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk AS runtime

# Set working directory
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
