# Build stage
FROM maven:3.8.4-eclipse-temurin-17-alpine AS build
WORKDIR /app

# TỐI ƯU 1: Chỉ copy file cấu hình trước
COPY pom.xml .

# TỐI ƯU 2: Tải trước các dependencies (thư viện).
# Layer này sẽ được cache lại, chỉ chạy lại khi file pom.xml thay đổi.
RUN mvn dependency:go-offline -B

# TỐI ƯU 3: Copy code sau cùng
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# TỐI ƯU 4: Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Thiết lập múi giờ và cấu hình (Nên mở lại nếu chạy thực tế)
ENV TZ=Asia/Ho_Chi_Minh

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
