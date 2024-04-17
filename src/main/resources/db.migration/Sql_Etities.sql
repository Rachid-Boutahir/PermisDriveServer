-- ==============================================================================================================
CREATE SEQUENCE IF NOT EXISTS sequence_permis START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_paiments START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_candidat START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_reclamations START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_planifications START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_administrateur START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_moniteurs START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_cours START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sequence_sujets START WITH 1 INCREMENT BY 1;

CREATE TABLE permis
(
    permis_id   INTEGER    NOT NULL,
    type_permis VARCHAR(5) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_permis PRIMARY KEY (permis_id)
);
-- ==============================================================================================================
CREATE TABLE paiments
(
    paiment_id       INTEGER NOT NULL,
    numero_paiment   VARCHAR(50),
    date_paiment     TIMESTAMP WITHOUT TIME ZONE,
    montant          DOUBLE PRECISION,
    avance           DOUBLE PRECISION,
    reste            DOUBLE PRECISION,
    is_paye          BOOLEAN,
    methode_paiement VARCHAR(50),
    CONSTRAINT pk_paiments PRIMARY KEY (paiment_id)
);
-- ==============================================================================================================
CREATE TABLE candidats
(
    candidat_id     INTEGER      NOT NULL,
    nom             VARCHAR(50)  NOT NULL,
    prenom          VARCHAR(50)  NOT NULL,
    sexe            VARCHAR(10)  NOT NULL,
    adresse         VARCHAR(100),
    ville           VARCHAR(50),
    telephone       VARCHAR(20),
    photos          VARCHAR(50),
    nom_utilisateur VARCHAR(50)  NOT NULL,
    email           VARCHAR(60) NOT NULL,
    mots_de_passe   VARCHAR(255) NOT NULL,
    date_rejoindre  TIMESTAMP WITHOUT TIME ZONE,
    groupe_sanguin  VARCHAR(5),
    is_deleted      BOOLEAN,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    deleted_at      TIMESTAMP WITHOUT TIME ZONE,
    paiment_id      BIGINT,
    CONSTRAINT pk_candidats PRIMARY KEY (candidat_id)
);

CREATE TABLE candidats_permis
(
    candidat_candidat_id INTEGER NOT NULL,
    permis_permis_id     INTEGER NOT NULL,
    CONSTRAINT pk_candidats_permis PRIMARY KEY (candidat_candidat_id, permis_permis_id)
);

-- ==============================================================================================================
CREATE TABLE reclamations
(
    reclamation_id VARCHAR     NOT NULL,
    objet          VARCHAR(40) NOT NULL,
    description    VARCHAR(40) NOT NULL,
    date_ajout     TIMESTAMP WITHOUT TIME ZONE,
    candidat_id    BIGINT,
    CONSTRAINT pk_reclamations PRIMARY KEY (reclamation_id)
);

ALTER TABLE reclamations
    ADD CONSTRAINT FK_RECLAMATIONS_ON_CANDIDAT FOREIGN KEY (candidat_id) REFERENCES candidats (candidat_id);
-- ==============================================================================================================
CREATE TABLE planifications
(
    planification_id     INTEGER     NOT NULL,
    nom_evenement        VARCHAR(50) NOT NULL,
    date_ajout           TIMESTAMP WITHOUT TIME ZONE,
    heur_debut           TIMESTAMP WITHOUT TIME ZONE,
    heur_fin             TIMESTAMP WITHOUT TIME ZONE,
    statut_planification VARCHAR(20),
    candidat_id          BIGINT,
    moniteur_id          BIGINT,
    CONSTRAINT pk_planifications PRIMARY KEY (planification_id)
);

-- ==============================================================================================================
CREATE TABLE administrateurs
(
    administrateur_id INTEGER      NOT NULL,
    nom               VARCHAR(50)  NOT NULL,
    prenom            VARCHAR(50)  NOT NULL,
    sexe              VARCHAR(10)  NOT NULL,
    adresse           VARCHAR(100),
    ville             VARCHAR(50)  NOT NULL,
    telephone         VARCHAR(20),
    photos            VARCHAR(50),
    nom_utilisateur   VARCHAR(50)  NOT NULL,
    email             VARCHAR(255) NOT NULL,
    mots_de_passe     VARCHAR(255) NOT NULL,
    is_deleted        BOOLEAN,
    created_at        TIMESTAMP WITHOUT TIME ZONE,
    updated_at        TIMESTAMP WITHOUT TIME ZONE,
    deleted_at        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_administrateurs PRIMARY KEY (administrateur_id)
);

-- ==============================================================================================================
CREATE TABLE moniteurs
(
    moniteur_id     INTEGER      NOT NULL,
    nom             VARCHAR(50)  NOT NULL,
    prenom          VARCHAR(50)  NOT NULL,
    sexe            VARCHAR(10)  NOT NULL,
    adresse         VARCHAR(100),
    ville           VARCHAR(50)  NOT NULL,
    telephone       VARCHAR(20),
    photos          VARCHAR(50),
    nom_utilisateur VARCHAR(50)  NOT NULL,
    email           VARCHAR(255) NOT NULL,
    mots_de_passe   VARCHAR(255) NOT NULL,
    type_moniteur   VARCHAR(20)  NOT NULL,
    date_rejoindre  TIMESTAMP WITHOUT TIME ZONE,
    salaire         DOUBLE PRECISION,
    is_deleted      BOOLEAN,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    deleted_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_moniteurs PRIMARY KEY (moniteur_id)
);

CREATE TABLE moniteurs_permis
(
    moniteur_id INTEGER NOT NULL,
    permis_id   INTEGER NOT NULL,
    CONSTRAINT pk_moniteurs_permis PRIMARY KEY (moniteur_id, permis_id)
);
-- ==============================================================================================================
CREATE TABLE cours
(
    cours_id    INTEGER          NOT NULL,
    lesson      VARCHAR(255)     NOT NULL,
    prix        DOUBLE PRECISION NOT NULL,
    description VARCHAR(255),
    moniteur_id BIGINT,
    CONSTRAINT pk_cours PRIMARY KEY (cours_id)
);

-- ==============================================================================================================
CREATE TABLE sujets
(
    sujet_id    INTEGER      NOT NULL,
    sujet       VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    cours_id    BIGINT,
    CONSTRAINT pk_sujets PRIMARY KEY (sujet_id)
);
-- =========================== CONSTRAINT unique  ===================================================================================
ALTER TABLE candidats
    ADD CONSTRAINT uc_candidats_email UNIQUE (email);

ALTER TABLE candidats
    ADD CONSTRAINT uc_candidats_nom_utilisateur UNIQUE (nom_utilisateur);

ALTER TABLE candidats
    ADD CONSTRAINT uc_candidats_paiment UNIQUE (paiment_id);

ALTER TABLE administrateurs
    ADD CONSTRAINT uc_administrateurs_email UNIQUE (email);

ALTER TABLE administrateurs
    ADD CONSTRAINT uc_administrateurs_nom_utilisateur UNIQUE (nom_utilisateur);

ALTER TABLE moniteurs
    ADD CONSTRAINT uc_moniteurs_email UNIQUE (email);

ALTER TABLE moniteurs
    ADD CONSTRAINT uc_moniteurs_nom_utilisateur UNIQUE (nom_utilisateur);

-- =============================== CONSTRAINT FK ===============================================================================
ALTER TABLE candidats
    ADD CONSTRAINT FK_CANDIDATS_ON_PAIMENT FOREIGN KEY (paiment_id) REFERENCES paiments (paiment_id);

ALTER TABLE candidats_permis
    ADD CONSTRAINT fk_canper_on_candidat FOREIGN KEY (candidat_candidat_id) REFERENCES candidats (candidat_id);

ALTER TABLE candidats_permis
    ADD CONSTRAINT fk_canper_on_permis FOREIGN KEY (permis_permis_id) REFERENCES permis (permis_id);

ALTER TABLE planifications
    ADD CONSTRAINT FK_PLANIFICATIONS_ON_CANDIDAT FOREIGN KEY (candidat_id) REFERENCES candidats (candidat_id);

ALTER TABLE planifications
    ADD CONSTRAINT FK_PLANIFICATIONS_ON_MONITEUR FOREIGN KEY (moniteur_id) REFERENCES moniteurs (moniteur_id);

ALTER TABLE moniteurs_permis
    ADD CONSTRAINT fk_monper_on_moniteur FOREIGN KEY (moniteur_id) REFERENCES moniteurs (moniteur_id);

ALTER TABLE moniteurs_permis
    ADD CONSTRAINT fk_monper_on_permis FOREIGN KEY (permis_id) REFERENCES permis (permis_id);

ALTER TABLE cours
    ADD CONSTRAINT FK_COURS_ON_MONITEUR FOREIGN KEY (moniteur_id) REFERENCES moniteurs (moniteur_id);

ALTER TABLE sujets
    ADD CONSTRAINT FK_SUJETS_ON_COURS FOREIGN KEY (cours_id) REFERENCES cours (cours_id);

-- ==============================================================================================================


