CREATE TABLE Evenement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    theme VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    lieu VARCHAR(255) NOT NULL
);
CREATE TABLE Intervenant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    specialite VARCHAR(255) NOT NULL
);
CREATE TABLE ParticipationEvenement (
    evenement_id INT,
    intervenant_id INT,
    PRIMARY KEY (evenement_id, intervenant_id),
    FOREIGN KEY (evenement_id) REFERENCES Evenement(id) ON DELETE CASCADE,
    FOREIGN KEY (intervenant_id) REFERENCES Intervenant(id) ON DELETE CASCADE
);
