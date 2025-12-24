# Etapa 1: Compilación (Build)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando tests para ir más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el jar generado en la etapa 1
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
