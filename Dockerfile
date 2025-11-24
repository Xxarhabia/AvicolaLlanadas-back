FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

CMD ["java", "-jar", "build/libs/GestionAvicolaLlanadas-0.0.1-SNAPSHOT.jar"]
