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
    adresse VARCHAR(50) NOT null,
    photo BLOB,
    telephone VARCHAR(10) NOT null,
    credit FLOAT NOT null
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
    utilisateur_id INT not NULL,
    CONSTRAINT fk_voiture_user FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(utilisateur_id) ON DELETE CASCADE
);

-- Création de la table "covoiturage"
CREATE TABLE IF NOT EXISTS covoiturage (
    covoiturage_id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    heure_depart TIME not null,
    duree TIME not null,
    lieu_depart VARCHAR(50) NOT NULL,
    lieu_arrivee VARCHAR(50) NOT NULL,
    statut ENUM('ACTIF', 'EN_COURS', 'TERMINE', 'VALIDE') NOT null,
    nb_place INT NOT null,
    prix_personne FLOAT NOT null,
    voiture_id INT NOT null,
     CONSTRAINT fk_covoiturage_voiture FOREIGN KEY (voiture_id)
        REFERENCES voiture(voiture_id) ON DELETE cascade
);

-- Création de la table "covoitureur"
CREATE TABLE IF NOT EXISTS covoitureur (
	covoitureur_id INT AUTO_INCREMENT PRIMARY KEY,
	role ENUM('PASSAGER', 'CONDUCTEUR') not NULL,
	validation_covoiturage BOOLEAN DEFAULT false,
	utilisateur_id INT not NULL,
	covoiturage_id INT not NULL,
    CONSTRAINT fk_covoitureur_user FOREIGN KEY (utilisateur_id)
        REFERENCES utilisateur(utilisateur_id) ON DELETE cascade,
    constraint fk_covoitureur_covoiturage foreign key (covoiturage_id)
    	REFERENCES covoiturage(covoiturage_id) ON DELETE cascade
);

-- Création de la table "avis" en relation vers "covoitureur"
CREATE TABLE IF NOT EXISTS avis (
	avis_id INT AUTO_INCREMENT PRIMARY KEY,
	commentaire VARCHAR(50) not NULL,
	note VARCHAR(50) not NULL,
	statut BOOLEAN DEFAULT false,
	covoitureur_id INT not NULL,
    CONSTRAINT fk_avis_covoitureur FOREIGN KEY (covoitureur_id)
        REFERENCES covoitureur(covoitureur_id) ON DELETE cascade
);

-- Création de la table "donnee_entreprise"
CREATE TABLE IF NOT EXISTS donnee_entreprise (
	donnee_entreprise_id INT AUTO_INCREMENT PRIMARY KEY,
	libelle VARCHAR(50) not NULL,
	valeur VARCHAR(500) not NULL
);
