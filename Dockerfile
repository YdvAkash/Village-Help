# ==============================
# STAGE 1: Build the application
# ==============================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only pom first (for dependency caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build jar (skip tests to avoid DB issues)
RUN ./mvnw clean package -DskipTests

# ==============================
# STAGE 2: Run the application
# ==============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
