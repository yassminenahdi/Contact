# Étape 1 : Choisir l'image de base
FROM openjdk:17-jdk-alpine

# Étape 2 : Ajouter un argument pour la version de votre application
ARG JAR_FILE=target/*.jar

# Étape 3 : Copier le fichier JAR dans l'image Docker
COPY ${JAR_FILE} app.jar

# Étape 4 : Exposer le port (si votre application écoute sur un port spécifique)
EXPOSE 8089

# Étape 5 : Définir la commande de démarrage de l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
