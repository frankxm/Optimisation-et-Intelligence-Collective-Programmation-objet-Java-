CREATE TABLE Genre(
    ID_gen INTEGER PRIMARY KEY,
    type TEXT,
    public_concerne TEXT
);

CREATE TABLE Film(
    ID_film INTEGER PRIMARY KEY,
    titre TEXT,
    annee TEXT,
    realisateur TEXT,
    genre TEXT,
    public_concerne TEXT
);

CREATE TABLE Critique(
    ID_crit INTEGER PRIMARY KEY,
    Ref_Film TEXT NOT NULL,
    provenance TEXT,
    date_de_parution TEXT,
    nombre_étoile TEXT,
    FOREIGN KEY (Ref_Film) REFERENCES Film(ID_film)
);



CREATE TABLE Cinema(
    ID_cine INTEGER PRIMARY KEY,
    nom TEXT,
    localisation TEXT,
    nb_salle TEXT
);


CREATE TABLE Programmation(
    ID_prog INTEGER PRIMARY KEY,
    Ref_cine TEXT,
    Ref_film TEXT,
    Date_debut TEXT,
    Date_fin TEXT
);


CREATE TABLE Acteur(
    ID_act INTEGER PRIMARY KEY,
    nom TEXT,
    prenom TEXT,
    sexe TEXT
);



CREATE TABLE Personnage(
    ID_perso INTEGER PRIMARY KEY,
    nom TEXT,
    prenom TEXT
    
);

CREATE TABLE Casting(
    ID_Cast INTEGER PRIMARY KEY,
    Ref_role TEXT NOT NULL,
    Ref_acteur TEXT NOT NULL,
    Ref_film TEXT NOT NULL,
    FOREIGN KEY (Ref_role) REFERENCES Role(ID_role),
    FOREIGN KEY (Ref_acteur) REFERENCES Acteur(ID_act),
    FOREIGN KEY (Ref_film) REFERENCES Film(ID_film)
);

CREATE TABLE Role(
    ID_role INTEGER PRIMARY KEY,
    titre TEXT,
    typ TEXT,
    raison_sociale TEXT
);


