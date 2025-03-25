# Etapa 1: Construcción del código
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Definir directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración y dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -U

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación (sin ejecutar tests para evitar fallos inesperados)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con OpenJDK optimizado
FROM eclipse-temurin:21-jdk-alpine

# Definir directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado desde la etapa anterior
COPY --from=build /app/target/servicio-usuarios-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponer el puerto de la aplicación
EXPOSE 8086

# Comando para ejecutar el servicio
CMD ["java", "-jar", "app.jar"]
