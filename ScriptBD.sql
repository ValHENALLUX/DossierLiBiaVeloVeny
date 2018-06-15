Drop table ordre_transport;
Drop table employe;
Drop table historique;
Drop table fiche_reparation;
Drop table velo;
Drop table station;
Drop table atelier;
Drop table entreprise;
Drop table localite;



create table Station (
	identifiant int not null,
    nom varchar(45) not null,
    nbVeloMinNorm int not null,
    nbVeloMinEx int not null,
    nbVeloMaxNorm int not null,
    nbVeloMaxEx int not null,
    dateCreation date not null,
    estCouverte boolean not null,
    coordGeo varchar(45),
    libelleRue varchar(45) not null,
    id_Localite int not null,
    constraint identifiant_pk primary key (identifiant),
    constraint Station_nbVeloMin_check check(nbVeloMinNorm > nbVeloMinEx),
    constraint Station_nbVeloMax_check check(nbVeloMaxNorm < nbVeloMaxEx))
    ENGINE = InnoDB ;


create table Localite (
	id int not null,
    libelle varchar(45) not null,
    codePostal int not null,
    constraint id_pk primary key (id),
    constraint localite_libelleCodePostal_unique unique (libelle,codePostal) )
    ENGINE = InnoDB ;
    
create table Velo (
	numVelo int not null,
    estEndommage boolean not null,
    dateAchat date not null,
    identifiant_Station int,
    nom_Entreprise varchar(45) not null,
    constraint numVelo_pk primary key (numVelo) )
    ENGINE = InnoDB ;
    
create table Fiche_reparation (
	numFiche int not null,
    dateDebut date not null,
    dateFin date not null,
    remarque varchar(45),
    ordreTravail varchar(45),
    estDeclasse boolean not null,
    numVelo_Velo int not null,
    identifiant_Atelier int,
    constraint numFiche_pk primary key (numFiche),
    constraint date_check check(dateDebut < dateFin) )
    ENGINE = InnoDB ;
	
create table Entreprise (
	nom varchar(45) not null,
    lieu varchar(45) not null,
    constraint nom_pk primary key(nom) )
    ENGINE = InnoDB ;
    
create table Historique (
	dateArrivee date not null,
    dateDepart date not null,
    identifiant_Station int not null,
    numVelo_Velo int not null,
    constraint date_check check(dateArrivee < dateDepart) )
    ENGINE = InnoDB ;
    
create table Employe (
	identifiant int not null,
    nom varchar(45) not null,
    prenom varchar(45) not null,
    initialPrenomSupp char(3),
    dateEmbauche date not null,
    telephone int,
    telephonePro int not null,
    email varchar(45),
    dateNaissance date not null,
    estATempsPartiel boolean not null,
    libelleRue varchar(45) not null,
    numRue int not null,
    typeEmploi varchar(45) not null,
    nbVeloDeclasse int,
    permiPlateau boolean,
    estResponsableZone boolean,
    identifiant_Atelier int,
    id_Localite int not null,
    id_Station int,
    responsable_Employe int,
    constraint identifiant_pk primary key (identifiant) )
    ENGINE = InnoDB ;
    
create table Ordre_Transport (
	numOrdre int not null,
    dateTransport date not null,
    numVelo_Velo int not null,
    identifiant_Station int,
    identifiant_Employe int not null,
    identifiant_Station_emettrice int not null,
    identifiant_Station_origine int,
    identifiant_Station_destination int,
    constraint numOrdre_pk primary key(numOrdre) )
    ENGINE = InnoDB ;
    
create table Atelier (
	identifiant int not null,
    lieu varchar(45) not null,
    constraint identifiant_pk primary key (identifiant) )
    ENGINE = InnoDB ;

Alter table Station add constraint Station_id_Localite_FK foreign key (id_Localite) references Localite (id);
Alter table Velo add constraint Velo_identifiant_Station_fk foreign key (identifiant_Station) references Station (identifiant);
Alter table Velo add constraint Velo_nom_Entreprise_fk foreign key (nom_Entreprise) references Entreprise (nom) ;
Alter table Fiche_Reparation add constraint Fiche_Reparation_numVelo_Velo_fk foreign key (numVelo_Velo) references Velo (numVelo) ;
Alter table Fiche_Reparation add constraint Fiche_Reparation_identifiant_Atelier_fk foreign key (identifiant_Atelier) references Atelier (identifiant);
Alter table Historique add constraint Historique_numVelo_Velo_fk foreign key (numVelo_Velo) references Velo (numVelo);
Alter table Historique add constraint Historique_identifiant_Station_fk foreign key (identifiant_Station) references Station (identifiant);
Alter table Employe add constraint Employe_identifiant_Atelier_fk foreign key (identifiant_Atelier) references Atelier (identifiant);
Alter table Employe add constraint Employe_id_Station_fk foreign key (id_Station) references Station (identifiant);
Alter table Employe add constraint Employe_id_Localite_fk foreign key (id_Localite) references Localite (id);
Alter table Employe add constraint Employe_responsable_Employe_fk foreign key (responsable_Employe) references Employe (identifiant);
Alter table Ordre_Transport add constraint Ordre_Transport_numVelo_Velo_fk foreign key (numVelo_Velo) references Velo (numVelo);
Alter table Ordre_Transport add constraint Ordre_Transport_identifiant_Station_fk foreign key (identifiant_Station) references Station (identifiant);
Alter table Ordre_Transport add constraint Ordre_Transport_identifiant_Employe_fk foreign key (identifiant_Employe) references Employe (identifiant);
