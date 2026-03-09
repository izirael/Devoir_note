-- Drop tables if they exist
DROP TABLE IF EXISTS resolution;
DROP TABLE IF EXISTS parametre;
DROP TABLE IF EXISTS operateur;
DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS matiere;
DROP TABLE IF EXISTS candidat;

-- Core Entities
CREATE TABLE candidat (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    matricule VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE matiere (
    id_matiere SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    coefficient NUMERIC DEFAULT 1
);

CREATE TABLE note (
    id SERIAL PRIMARY KEY,
    id_candidat INTEGER REFERENCES candidat(id),
    id_matiere INTEGER REFERENCES matiere(id_matiere),
    id_correcteur INTEGER,
    valeur_note NUMERIC NOT NULL
);

-- Dynamic Calculation Engine
CREATE TABLE operateur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    symbole VARCHAR(10) NOT NULL -- ex: +, -, *, /
);

CREATE TABLE parametre (
    id SERIAL PRIMARY KEY,
    id_operateur INTEGER REFERENCES operateur(id),
    valeur_gauche VARCHAR(255), -- Could be a field name or a constant
    valeur_droite VARCHAR(255)
);

CREATE TABLE resolution (
    id SERIAL PRIMARY KEY,
    description TEXT,
    resultat NUMERIC
);

-- Initial Data for Operators
INSERT INTO operateur (nom, symbole) VALUES ('Addition', '+'), ('Soustraction', '-'), ('Multiplication', '*'), ('Division', '/');
