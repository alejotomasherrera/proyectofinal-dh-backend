# Etapa 1: Construcción del código
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos necesarios para dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -U

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Verificar si el JAR se generó correctamente
RUN ls -l target/

# Etapa 2: Imagen final con OpenJDK optimizado
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar /app/app.jar

# Exponer el puerto de Eureka Server
EXPOSE 8761

# Comando para ejecutar el servicio
CMD ["java", "-jar", "/app/app.jar"]
