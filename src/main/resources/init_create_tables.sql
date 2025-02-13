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
    credit FLOAT NOT null,
    statut ENUM('ACTIF', 'INACTIF', 'SUSPENDU')
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
    date VARCHAR(10) not NULL,
    heure_depart TIME not null,
    duree TIME not null,
    lieu_depart VARCHAR(50) NOT NULL,
    lieu_arrivee VARCHAR(50) NOT NULL,
    statut ENUM('ACTIF', 'EN_COURS', 'TERMINE', 'VALIDE') NOT null,
    nb_place INT NOT null,
    prix_personne FLOAT NOT null,
    voiture_id INT NOT null,
    conducteur_id INT not null,
     CONSTRAINT fk_covoiturage_voiture FOREIGN KEY (voiture_id)
        REFERENCES voiture(voiture_id) ON DELETE cascade,
    CONSTRAINT fk_covoiturage_conducteur FOREIGN KEY (conducteur_id)
        REFERENCES utilisateur(utilisateur_id) ON DELETE cascade
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
	decision ENUM('ACCEPTE', 'REFUSE') default NULL,
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

-- Création de la table "statistique"
CREATE TABLE IF NOT EXISTS statistique (
	statistique_id INT AUTO_INCREMENT PRIMARY KEY,
	date VARCHAR(10) not NULL,
	type VARCHAR(50) not NULL,
	valeur INT not NULL
);



-- Création du trigger après la mise à jour de la table "covoiturage"
DELIMITER $$

CREATE TRIGGER after_covoiturage_update
AFTER UPDATE ON covoiturage
FOR EACH ROW
BEGIN
    DECLARE credit_count INT;
    DECLARE covoiturage_count INT;
    DECLARE today_date VARCHAR(10);

    -- Obtenir la date actuelle au format 'YYYY-MM-DD'
    SET today_date = DATE_FORMAT(NOW(), '%Y-%m-%d');

    IF NEW.statut = 'VALIDE' AND OLD.statut <> 'VALIDE' THEN

        -- Vérifier si une ligne "credit" existe déjà pour aujourd'hui
        SELECT COUNT(*) INTO credit_count FROM statistique WHERE type = 'credit' AND date = today_date;

        IF credit_count > 0 THEN
            UPDATE statistique
            SET valeur = valeur + 2
            WHERE type = 'credit' AND date = today_date;
        ELSE
            INSERT INTO statistique (type, valeur, date) VALUES ('credit', 2, today_date);
        END IF;

        -- Vérifier si une ligne "covoiturage" existe déjà pour aujourd'hui
        SELECT COUNT(*) INTO covoiturage_count FROM statistique WHERE type = 'covoiturage' AND date = today_date;

        IF covoiturage_count > 0 THEN
            UPDATE statistique
            SET valeur = valeur + 1
            WHERE type = 'covoiturage' AND date = today_date;
        ELSE
            INSERT INTO statistique (type, valeur, date) VALUES ('covoiturage', 1, today_date);
        END IF;
    END IF;
END$$

DELIMITER ;






