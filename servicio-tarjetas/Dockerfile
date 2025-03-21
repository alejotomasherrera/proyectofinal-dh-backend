# Crear docker file de servicio-tarjetas en puerto 8086

FROM openjdk:17-jdk-alpine

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo JAR generado por el build de tu servicio
COPY target/servicio-tarjetas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8086

# Comando para
# ejecutar el JAR del microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]