# Usamos Java 21 (versión ligera Alpine)
FROM eclipse-temurin:21-jre-alpine

# Creamos la carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el .jar que genera Maven a la imagen
COPY target/*.jar app.jar

# Comando para iniciar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
