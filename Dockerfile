# Build stage
FROM maven:3.8.4-eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# # Set environment variables
# ENV SPRING_PROFILES_ACTIVE=prod
# ENV TZ=Asia/Ho_Chi_Minh

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
