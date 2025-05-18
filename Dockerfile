# Usa una imagen base de Java
FROM openjdk:21

# Crea el directorio de la app
WORKDIR /app

# Copia el archivo JAR
COPY target/ServiceUser-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (ajústalo si tu app usa otro)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
