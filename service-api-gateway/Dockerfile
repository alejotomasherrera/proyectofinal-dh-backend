# Configurar dockerfile de api gatway en puerto 8081

FROM openjdk:17-jdk-alpine

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo JAR generado por el build de tu servicio
COPY target/service-api-gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081


# Comando para
# ejecutar el JAR del microservicio

ENTRYPOINT ["java", "-jar", "app.jar"]