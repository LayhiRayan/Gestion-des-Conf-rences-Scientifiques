
# 🎤 Gestion des Conférences Scientifiques

## 📌 Description
**Gestion des Conférences Scientifiques** est une application de bureau développée en **Java (Swing)** avec **MySQL** comme base de données.  
Elle permet de gérer des **événements scientifiques**, leurs **intervenants**, et les **associations entre les deux**.

---

## ✅ Fonctionnalités

- 🎟 **Ajouter un événement**  
  Créez un nouvel événement avec son **titre**, **thème**, **date** et **lieu**.

- 👨‍🏫 **Gérer les intervenants**  
  Ajoutez, modifiez ou supprimez des intervenants spécialisés dans un domaine.

- 📝 **Associer des intervenants à un événement**  
  Affectez plusieurs intervenants à un événement via une interface dédiée.

- 🔎 **Recherche d’événements par date**

- 🔍 **Recherche d’intervenants par nom**

- 🔐 **Authentification utilisateur**  
  Connexion sécurisée avec **gestion de mot de passe** et **question de sécurité**.

---

## 🏗 Structure de la Base de Données

Nom de la base de données : `gestion_conferences`

| Table                     | Description                                         |
|---------------------------|-----------------------------------------------------|
| `intervenant`             | Informations sur les intervenants                  |
| `evenement`               | Détails des événements (titre, thème, date, lieu)  |
| `participation_evenement`| Relation plusieurs-à-plusieurs entre les deux      |
| `user`                    | Stocke les informations de connexion des utilisateurs |

---

## 📊 Schéma de la Base de Données (SQL)

```sql
CREATE DATABASE IF NOT EXISTS gestion_conferences;
USE gestion_conferences;

CREATE TABLE IF NOT EXISTS intervenant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    specialite VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS evenement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    theme ENUM('SCIENCE', 'TECHNOLOGIE', 'INNOVATION', 'EDUCATION') NOT NULL,
    date_evenement DATE NOT NULL,
    lieu VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS participation_evenement (
    evenement_id INT NOT NULL,
    intervenant_id INT NOT NULL,
    FOREIGN KEY (evenement_id) REFERENCES evenement(id) ON DELETE CASCADE,
    FOREIGN KEY (intervenant_id) REFERENCES intervenant(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user (
    login VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    question_securite VARCHAR(255),
    reponse_securite VARCHAR(255)
);
```

---
## 🧱 Architecture en Couches – Gestion des Conférences Scientifiques


## 🏗️ Structure Complète du Projet GestionConferences

```
GestionConferences/
│
├── beans/                        # 🧩 Modèles de données (JavaBeans)
│   ├── EThemeEvenement.java           # Enum des thèmes de conférence
│   ├── Evenement.java                 # Classe de l’événement
│   ├── Intervenant.java               # Classe de l’intervenant
│   ├── ParticipationEvenement.java   # Classe de liaison événement ↔ intervenant
│   └── User.java                      # Classe utilisateur
│
├── connexion/                    # 🔌 Connexion à la base de données
│   └── Connexion.java                 # Classe de connexion unique (singleton)
│
├── dao/                         # 📦 Interfaces DAO
│   ├── IDao.java                     # Interface générique CRUD
│   └── IUserDao.java                 # Interface spécifique utilisateur
│
├── gui/                         # 🖥️ Interface utilisateur (Swing)
│   ├── ConferenceBarChart.java       # Diagramme de conférences
│   ├── EvenementForm.java            # Création/modification d’événement
│   ├── EvenementParDate.java         # Recherche par date
│   ├── IntervenantByNom.java         # Recherche par nom
│   ├── IntervenantForm.java          # Formulaire intervenant
│   ├── MDIApplication.java           # Fenêtre principale (interface multi-documents)
│   ├── Main.java                     # Classe principale (avec `main`)
│   └── *.png                         # Images d’illustration (logo, interface)
│
├── jdbc/                        # 📡 Connexions manuelles JDBC (tests ou essais)
│   ├── JDBC.java
│   ├── JDBC2.java
│   └── JDBC3.java
│
├── services/                    # 🧠 Logique métier (service layer)
│   ├── EvenementService.java
│   ├── IntervenantService.java
│   ├── ParticipationEvenementService.java
│   └── UserService.java
│
├── test/                        # 🧪 Tests simples
│   └── Test.java
│
├── util/                        # 🛠️ Classes utilitaires
│   └── SecurityUtil.java
│
└── README.md                    # 📘 Documentation du projet
```


## 📐 Modélisation UML

### 📌 Diagramme de Cas d’Utilisation
- Ajouter un événement
- Gérer les intervenants
- Associer intervenant à un événement
- Rechercher un événement/intervenant
- Se connecter

### 🧩 Diagramme de Classes Simplifié

```
+--------------------+        +------------------------+
|    Intervenant     |        |       Evenement        |
+--------------------+        +------------------------+
| - id               |        | - id                   |
| - nom              |        | - titre                |
| - prenom           |        | - theme                |
| - specialite       |        | - date_evenement       |
+--------------------+        | - lieu                 |
                              +------------------------+
          \                            /
           \                          /
            \________________________/
             (participation_evenement)
```

---

## 🧰 Technologies et Bibliothèques Utilisées

- **Java 8**  
- **Swing** (interface graphique)  
- **MySQL** (base de données relationnelle)  
- **JDBC** (Java Database Connectivity)  
- **Maven** (ou structure manuelle)  
- **UML (StarUML, Lucidchart, etc.)**

---



---

## 🏗 Visualisation de l’Architecture

```
[ UI (Swing) ] ←→ [ DAO (Data Access Objects) ] ←→ [ MySQL Database ]
       ↑
       |
     Utilisateur
```

---

## 🎥 Vidéo Démonstrative

➡️ Une vidéo de démonstration est disponible pour présenter :

- Le lancement de l'application
- L’ajout/modification d’événements et intervenants
- L'association entre intervenants et événements
- La navigation et l’interface utilisateur
- Visualisation des statistiques(nombre des intervenants par conference)



---

## 📌 Auteur
Développé par **Rayan LAYHI** – Étudiant à **Ecole normale supérieure**  
Dans le cadre du projet : **Programmation Java & Bases de Données**
```

Tu veux aussi que je t’aide à faire une version anglaise ?
