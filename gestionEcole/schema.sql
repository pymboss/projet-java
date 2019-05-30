CREATE DATABASE INFO_ECOLE;


USE INFO_ECOLE;

CREATE TABLE Ecole 
 ( id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
   nom VARCHAR(50) NOT NULL,
   PRIMARY KEY (id),
   );
   
CREATE TABLE Niveau 
( id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
  nom VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);   

CREATE TABLE AnneeScolaire 
( id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
  debut DATE NOT NULL,
  fin DATE NOT NULL,
  PRIMARY KEY (id),
  );

CREATE TABLE Discipline 
( id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
  nom VARCHAR2(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Personne
(id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
 nom VARCHAR(50) NOT NULL , 
 prenom VARCHAR(50) NOT NULL ,
 type VARCHAR(1),
 PRIMARY KEY (id)
);

CREATE TABLE Trimestre 
( id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
  numero INTEGER, 
  debut DATE NOT NULL,
  fin DATE NOT NULL, 
  id_annee  SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT fk_trimestre_annee      
        FOREIGN KEY (id_annee)     
        REFERENCES AnneeScolaire(id) 
        ON DELETE RESTRICT
 );


CREATE TABLE Classe 
( id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
  nom VARCHAR2(50) NOT NULL,
  id_ecole  SMALLINT UNSIGNED NOT NULL ,
  id_niveau  SMALLINT UNSIGNED NOT NULL ,
  id_annee  SMALLINT UNSIGNED NOT NULL , 
  PRIMARY KEY (id)
  CONSTRAINT fk_classe_annee      
        FOREIGN KEY (id_annee)     
        REFERENCES AnneeScolaire(id) 
        ON DELETE RESTRICT,
  CONSTRAINT fk_classe_ecole    
        FOREIGN KEY (id_ecole)     
        REFERENCES Ecole(id)
        ON DELETE RESTRICT,
  CONSTRAINT fk_classe_niveau   
        FOREIGN KEY (id_niveau)     
        REFERENCES Niveau(id)
        ON DELETE RESTRICT,    
  
 );




CREATE TABLE Enseignement
(id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, 
 id_classe  SMALLINT UNSIGNED NOT NULL ,
 id_discipline  SMALLINT UNSIGNED NOT NULL,
 id_personne  SMALLINT UNSIGNED NOT NULL ,
 PRIMARY KEY (id) ,
  CONSTRAINT fk_enseignement_classe      
        FOREIGN KEY (id_classe)     
        REFERENCES Classe(id) 
        ON DELETE RESTRICT,
  CONSTRAINT fk_enseignement_discipline  
        FOREIGN KEY (id_discipline)     
        REFERENCES Discipline(id)
        ON DELETE RESTRICT,
  CONSTRAINT fk_enseignement_personne
        FOREIGN KEY (id_personne)     
        REFERENCES Personne(id)  
        ON DELETE RESTRICT
);

CREATE TABLE Inscription
(id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
 id_classe  SMALLINT UNSIGNED NOT NULL ,
 id_personne  SMALLINT UNSIGNED NOT NULL ,
 PRIMARY KEY (id),
 CONSTRAINT fk_inscription_classe      
        FOREIGN KEY (id_classe)     
        REFERENCES Classe(id) 
        ON DELETE RESTRICT,
 CONSTRAINT fk_inscription_personne
        FOREIGN KEY (id_personne)     
        REFERENCES Personne(id)  
        ON DELETE RESTRICT
);

CREATE TABLE Bulletin
(id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
 id_trimestre SMALLINT UNSIGNED NOT NULL , 
 id_inscription  SMALLINT UNSIGNED NOT NULL ,
 appreciation VARCHAR2(100),
 PRIMARY KEY (id),
 CONSTRAINT fk_bulletin_inscription      
        FOREIGN KEY (id_inscription)     
        REFERENCES Inscription(id) 
        ON DELETE RESTRICT,
 CONSTRAINT fk_bulletin_trimestre
        FOREIGN KEY (id_trimestre)     
        REFERENCES Trimestre(id)
        ON DELETE RESTRICT
);

CREATE TABLE DetailBulletin
(id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
 id_bulletin  SMALLINT UNSIGNED NOT NULL  , 
 id_enseignement  SMALLINT UNSIGNED NOT NULL ,
 appreciation VARCHAR2(100),
 PRIMARY KEY (id),
 PRIMARY KEY (id),
 CONSTRAINT fk_DetailBulletin_Bulletin      
        FOREIGN KEY (id_bulletin)     
        REFERENCES Bulletin(id) ,
 CONSTRAINT fk_DetailBulletin_Enseignement
        FOREIGN KEY (id_enseignement)     
        REFERENCES Enseignement(id)
        ON DELETE RESTRICT
);

CREATE TABLE Evaluation
(id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT , 
 id_detail_bulletin  SMALLINT UNSIGNED NOT NULL , 
 note  NUMERIC NOT NULL, 
 appreciation VARCHAR2(100),
 PRIMARY KEY (id),
 CONSTRAINT fk_Evaluation_DetailBulletin      
        FOREIGN KEY (id_detail_bulletin)     
        REFERENCES DetailBulletin(id) 
        ON DELETE RESTRICT  

);