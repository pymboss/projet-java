insert into Ecole (NOM ) values ('Ecole Jules Ferry');

insert into AnneeScolaire (DEBUT, FIN) values ('01/09/2018','07/07/2019');
insert into AnneeScolaire (DEBUT, FIN) values ('01/09/2019','07/07/2020');

insert into Trimestre (NUMERO, DEBUT, FIN, ID_ANNEE ) values (1,'01/09/2018','07/12/2018',1);
insert into Trimestre (NUMERO, DEBUT, FIN, ID_ANNEE ) values (2,'08/12/2018','01/04/2019',1);
insert into Trimestre (NUMERO, DEBUT, FIN, ID_ANNEE ) values (3,'02/04/2019','07/07/2019',1);

insert into Niveau (NOM) values ('Prepa1');
insert into Niveau (NOM) values ('Prepa2');
insert into Niveau (NOM) values ('ing1');

insert into Classe (NOM, ID_ECOLE, ID_NIVEAU, ID_ANNEE) values ('TD1',1, 1 , 1);
insert into Classe (NOM, ID_ECOLE, ID_NIVEAU, ID_ANNEE) values ('TD2', 1,1,1);