# 🛵 EcoRide - BackEnd

Backend de l'application **EcoRide**, développé avec **Spring Boot**, **Java 17** et **MySQL**.  
Ce service gère la logique métier, les API REST et la connexion à la base de données.

---

## 🛠️ Prérequis

Avant de lancer le projet, assure-toi d'avoir installé :

- [Java 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://dev.mysql.com/downloads/)
- [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)
- Un SGBD (ex: [DBeaver](https://dbeaver.io/)) pour visualiser la base de données

---

## ⚙️ Configuration de la base de données

Le projet utilise une base MySQL distante (via ClearDB sur Heroku).  
Les informations de connexion sont configurées dans le fichier `application.yml`.

### 🔧 Connexion avec un SGBD (DBeaver)

1. Ouvre DBeaver
2. Crée une nouvelle connexion MySQL
3. Renseigne les identifiants ci-dessus
4. Ou récupère les infos directement dans la section `datasource` du fichier `application.yml`

### 🚨 Erreur fréquente : ClearDB saturé

> Heroku peut refuser les connexions si le quota gratuit est dépassé.  
> Dans ce cas, redémarre la base de données avec la commande suivante :

```bash
heroku restart cleardb --app ecoride-database


### 🚀 Lancer le projet

💡 Important : Le backend doit être lancé avant le frontend.
mvn clean install

### 🧰 Stack technique
* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* Heroku (ClearDB)

### ✅ Bonnes pratiques
* Organiser les packages par couche : controller, service, repository, model

* Utiliser des DTO pour exposer les données

* Centraliser la configuration dans application.yml

* Ne jamais versionner les mots de passe ou clés d’API en clair (utiliser des variables d’environnement)

### 🤝 Contribution
Les contributions sont bienvenues pour améliorer ce backend !
