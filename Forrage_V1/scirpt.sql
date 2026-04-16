Typedevis liste deroulante : (etude ,forrage)
Montant calculena fa ts anaty base , prix unitaire no mipotra 

anaty devis detail misy prix unitaire sy quantite 

CREATE TABLE parametres_remises (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL, 
    seuil INTEGER NOT NULL,
    pourcentage DECIMAL(5, 2) NOT NULL,
    actif BOOLEAN DEFAULT TRUE
);

