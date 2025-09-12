

# 🏡 Estates Backend

Backend pour le projet Estates, développé en Java avec Spring Boot et utilisant une base de données MySQL.


## 🛠️ Fonctionnalités

- Authentification sécurisée avec JWT
- Création / modifications d'annonces
- Envoi de messages
- Upload de photos via Cloudinary
- Connexion à une base de données MySQL


## 🚀 Prérequis

- **Java 17**
- **Spring Boot 3.5.5**
- **MySQL**
- **Cloudinary** pour le stockage des images
- **Maven** pour la gestion des dépendances

## 📦 Installation

1. Cloner le projet
```bash 
git clone https://github.com/ThomasLebel/estates-backend.git
cd estates-backend
```
2. Créer la base de données MySQL
- Executez le script présent dans ressources/sql/script.sql pour générer la base de donnée
```bash 
mysql -u <utilisateur> -p <nom_de_la_base> < resources/sql/script.sql
```
3. Configurer les variables d’environnement
- Renommez le fichier example.env en .env
- Remplissez la variable dans le fichier .env
```bash 
DB_URL=jdbc:mysql://localhost:3306/nom_de_la_base
DB_USERNAME=votre_utilisateur
DB_PASSWORD=mot_de_passe

CLOUDINARY_CLOUD_NAME=votre_cloud_name
CLOUDINARY_API_KEY=votre_api_key
CLOUDINARY_API_SECRET=votre_api_secret
```

4. Installer les dépendances et lancer l’application
```bash 
mvn clean install
mvn spring-boot:run
```
L’application devrait être accessible sur http://localhost:3001 par défaut

## 📖 Documentation API (Swagger)
Une fois l’application démarrée, vous pouvez consulter la documentation interactive des routes via Swagger :
👉 http://localhost:8080/swagger-ui/index.html
Cette interface permet de visualiser l’ensemble des endpoints disponibles et de les tester directement.

## 🗃️ Utilisation de Cloudinary
1. Créer un compte sur Cloudinary
2. Récupérer vos identifiants (cloud name, API key, API secret) dans le tableau de bord.
3. Les ajouter dans votre fichier .env
4. Les images seront stockées dans un dossier "estates" sur votre compte cloudinary

## 🧩 ️Dépendances
Le projet utilise les dépendances suivantes :

- Spring Boot
    - spring-boot-starter-data-jpa → persistance avec JPA/Hibernate
    - spring-boot-starter-security → sécurité et gestion des accès
    - spring-boot-starter-web → création d’API REST
    - spring-boot-starter-oauth2-resource-server → authentification OAuth2/JWT
    - spring-boot-devtools → rechargement automatique en développement
    - spring-boot-starter-validation → validation des données
    - spring-boot-starter-test → tests unitaires et d’intégration

- Base de données
    - mysql-connector-j → driver MySQL

- Utilitaires
    - lombok → génération automatique de code (getters/setters, etc.)
    - spring-dotenv → gestion des variables d’environnement avec .env

- Sécurité / Tests
    - spring-security-test → tests liés à la sécurité

- Swagger / OpenAPI
    - springdoc-openapi-starter-webmvc-ui (2.8.5) → documentation Swagger

- Cloudinary
    - cloudinary-http44 (1.30.0) → upload et gestion des images

