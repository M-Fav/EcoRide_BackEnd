# EcoRide_BackEnd
### Installer Java 17

### Installer Maven

### Installer MySQL

### Installer heroku CLI

### Connecter la base de donnée : 
* prendre un SGBD comme DBeaver et se servir des informations dans la partie datasource du application.yml
* si besoin, prendre les identifiants de la bdd (mysql://fnjv44kcr8ym4e3e:nm7i2x00jsuv7obm@e7qyahb3d90mletd.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/v6eqjfqi1n8d9orn
* Username : fnjv44kcr8ym4e3e
* Password : nm7i2x00jsuv7obm
* Port: 3306)
* (sera changé et supprimé du ReadMe après la correction)

#### il se peut que la version gratuite de heroku bloque la base de donnée car trop de connexions simultanées.
* si c'est le cas taper cette commande dans le terminal : heroku restart cleardb --app ecoride-database

## Attention, lancer le projet Backend avant le projet Frontend.

### Taper la commande dans le terminal: 
* mvn clean install

### Pour lancer le projet : Run sur BackendApplication.java



