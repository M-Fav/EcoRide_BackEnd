-- Création de la table "utilisateur"
CREATE TABLE IF NOT EXISTS utilisateur (
    utilisateur_id INT AUTO_INCREMENT PRIMARY KEY,
    pseudo VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,
    role ENUM('UTILISATEUR', 'EMPLOYE', 'ADMINISTRATEUR') NOT null,
    nom  VARCHAR(50) NOT null,
    prenom VARCHAR(50) NOT null,
    date_naissance DATE NOT null,
    adresse VARCHAR(50) not null,
    photo BLOB,
    telephone VARCHAR(10) not null
);

-- Création de la table "token" avec relation vers "utilisateur"
CREATE TABLE IF NOT EXISTS token (
    token_id INT AUTO_INCREMENT PRIMARY KEY,
    access_token VARCHAR(255) NOT NULL, 
    refresh_token VARCHAR(255) NOT NULL,
    is_logged_out BOOLEAN DEFAULT FALSE,
    utilisateur_id INT,
    CONSTRAINT fk_token_user FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(utilisateur_id) ON DELETE CASCADE
);

-- Création de la table "voiture" avec relation vers "utilisateur"
CREATE TABLE IF NOT EXISTS voiture (
    voiture_id INT AUTO_INCREMENT PRIMARY KEY,
    modele VARCHAR(50) NOT NULL,
    immatriculation VARCHAR(9) NOT NULL UNIQUE,
    energie ENUM('ESSENCE', 'DIESEL', 'HYBRIDE', 'ELECTRIQUE') NOT NULL,
    couleur VARCHAR(50) NOT NULL,
    date_premiere_immatriculation DATE NOT NULL, 
    marque ENUM(
        'TOYOTA', 'VOLKSWAGEN', 'MERCEDES_BENZ', 'BMW', 'AUDI', 
        'FORD', 'HONDA', 'CHEVROLET', 'NISSAN', 'HYUNDAI',
        'KIA', 'PEUGEOT', 'RENAULT', 'TESLA', 'FIAT', 
        'JEEP', 'VOLVO', 'LAND_ROVER', 'PORSCHE', 'MAZDA',
        'SUBARU', 'SUZUKI', 'LEXUS', 'FERRARI', 'LAMBORGHINI', 
        'ASTON_MARTIN', 'BUGATTI', 'MASERATI', 'BENTLEY', 'ROLLS_ROYCE'
    ) NOT NULL,
    utilisateur_id INT,
    CONSTRAINT fk_voiture_user FOREIGN KEY (utilisateur_id) 
        REFERENCES utilisateur(utilisateur_id) ON DELETE CASCADE
);

-- Création de la table "covoiturage"
CREATE TABLE IF NOT EXISTS covoiturage (
    covoiturage_id INT AUTO_INCREMENT PRIMARY KEY,
    date_depart DATE NOT NULL,
    date_arrivee DATE NOT NULL,
    heure_depart DATE not null,
    heure_arrivee DATE not null,
    lieu_depart VARCHAR(50) NOT NULL,
    lieu_arrivee VARCHAR(50) NOT NULL,
    statut ENUM('ACTIF', 'EN_COURS', 'TERMINE') not NULL,
    nb_place INT not null,
    prix_personne INT not null
);

-- Création de la table "covoitureur"
CREATE TABLE IF NOT EXISTS covoitureur (
	covoitureur_id INT AUTO_INCREMENT PRIMARY KEY,
	role ENUM('PASSAGER', 'CONDUCTEUR'),
	validation_covoiturage BOOLEAN DEFAULT false,
	utilisateur_id INT,
	covoiturage_id INT,
    CONSTRAINT fk_covoitureur_user FOREIGN KEY (utilisateur_id) 
        REFERENCES utilisateur(utilisateur_id) ON DELETE cascade,
    constraint fk_covoitureur_covoiturage foreign key (covoiturage_id)
    	REFERENCES covoiturage(covoiturage_id) ON DELETE cascade
);

-- Création de la table "avis" en relation vers "covoitureur"
CREATE TABLE IF NOT EXISTS avis (
	avis_id INT AUTO_INCREMENT PRIMARY KEY,
	commentaire VARCHAR(50),
	note VARCHAR(50),
	statut BOOLEAN DEFAULT false,
	covoitureur_id INT,
    CONSTRAINT fk_avis_covoitureur FOREIGN KEY (covoitureur_id) 
        REFERENCES covoitureur(covoitureur_id) ON DELETE cascade
);