# Image de base avec Java
FROM openjdk:17

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copie du jar généré avec mvn clean package dans le conteneur
COPY target/StockManagementUsingOop-1.0-SNAPSHOT.jar .

# Commande pour exécuter l'application lorsque le conteneur démarre
CMD ["java", "-jar", "StockManagementUsingOop-1.0-SNAPSHOT.jar"]
