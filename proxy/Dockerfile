# ==============================
# Etapa 1: Build (Compilación)
# ==============================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Copiamos solo el pom.xml primero para descargar dependencias
#    Esto permite que Docker use la caché si no has cambiado dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copiamos el código fuente
COPY src ./src

# 3. Compilamos el proyecto (saltando tests para agilizar el build en contenedores)
RUN mvn clean package -DskipTests

# ==============================
# Etapa 2: Runtime (Ejecución)
# ==============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 4. Copiamos solo el JAR construido en la etapa anterior
#    El nombre 'app.jar' simplifica el ENTRYPOINT
COPY --from=build /app/target/*.jar app.jar

# 5. Exponemos el puerto (informativo, coincide con tu application.yml)
EXPOSE 8080

# 6. Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
