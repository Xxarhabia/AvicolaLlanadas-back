FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Dar permisos de ejecución a gradlew
RUN chmod +x ./gradlew

# Construir el JAR
RUN ./gradlew bootJar --no-daemon

# Ejecutar el JAR
CMD ["java", "-jar", "build/libs/GestionAvicolaLlanadas-0.0.1-SNAPSHOT.jar"]
