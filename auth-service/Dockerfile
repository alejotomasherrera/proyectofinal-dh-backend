FROM openjdk:17-jdk-alpine

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo JAR generado por el build de tu servicio
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

# Comando para ejecutar el JAR del microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]