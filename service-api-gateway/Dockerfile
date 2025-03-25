# Etapa 1: Construcción del código
FROM maven:3.9.8-eclipse-temurin-17 AS build

# Definir directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración y dependencias primero para aprovechar caché
COPY pom.xml .
RUN mvn dependency:go-offline -U

# Copiar el código fuente de la API Gateway
COPY src ./src

# Compilar la aplicación (sin ejecutar tests para evitar fallos inesperados)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con OpenJDK 17 optimizado
FROM eclipse-temurin:17-jdk-alpine

# Definir directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR compilado desde la etapa anterior
COPY --from=build /app/target/service-api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del API Gateway
EXPOSE 8080

# Comando para ejecutar el API Gateway
CMD ["java", "-jar", "app.jar"]
