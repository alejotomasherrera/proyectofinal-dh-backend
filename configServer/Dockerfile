# Etapa de compilación
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos necesarios para resolver dependencias primero (mejora el caching)
COPY pom.xml .
RUN mvn dependency:go-offline -U

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Verificar el nombre del JAR generado
RUN ls -l target/

# Etapa de ejecución
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar /app/app.jar

# Exponer el puerto en el que corre la app
EXPOSE 8888

# Comando para ejecutar el servicio
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
