# --- Bước 1: Build với Maven và JDK 21 ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

# --- Bước 2: Chạy app ---
FROM eclipse-temurin:21-jdk
WORKDIR /app

# copy đúng file .jar từ target
COPY --from=build /app/target/WebsiteBanCayCanh-0.0.1-SNAPSHOT.jar app.jar

# chạy app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
