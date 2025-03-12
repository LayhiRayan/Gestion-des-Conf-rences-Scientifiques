
CREATE DATABASE gestionconferences;
USE gestionconferences;


CREATE TABLE evenement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    theme VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    lieu VARCHAR(255) NOT NULL
);


CREATE TABLE intervenant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    specialite VARCHAR(255) NOT NULL
);


CREATE TABLE participationevenement (
    evenement_id INT NOT NULL,
    intervenant_id INT NOT NULL,
    PRIMARY KEY (evenement_id, intervenant_id),
    FOREIGN KEY (evenement_id) REFERENCES evenement(id) ON DELETE CASCADE,
    FOREIGN KEY (intervenant_id) REFERENCES intervenant(id) ON DELETE CASCADE
);
