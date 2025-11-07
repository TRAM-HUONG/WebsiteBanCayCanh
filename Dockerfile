# --- Bước 1: Build với Maven và JDK 21 ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

# --- Bước 2: Run WAR ---
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.war app.war
EXPOSE 8080
CMD ["java", "-jar", "app.war"]
