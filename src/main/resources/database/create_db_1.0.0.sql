create table IMAGENES(
	ID bigint not null AUTO_INCREMENT,
	FILE_NAME varchar(100) NOT NULL,
	CONTENT_TYPE varchar(25) NULL,
	CONTENT MEDIUMBLOB NOT NULL,
	PRIMARY KEY(ID)
);

create table USUARIOS(
	ID bigint not null AUTO_INCREMENT,
	FECHA_CREACION timestamp not null,
	EMAIL varchar(75) not null,
	USERNAME varchar(50) not null,
	FULL_NAME varchar(100) null,
	IMAGEN_ID bigint null,
	PRIMARY KEY(ID)
);

alter table USUARIOS add constraint USR$IMG foreign key (IMAGEN_ID) references IMAGENES(ID);

create table PREGUNTAS(
	ID bigint not null AUTO_INCREMENT,
	CODIGO varchar(50) not null,
	TEXTO varchar(250) not null,
	PUNTOS smallint not null,
	CATEGORIA varchar(25) not null,
	PRIMARY KEY(ID)
);

create table PREGUNTAS_BINARIAS(
	ID bigint not null,
	OPCION_CORRECTA tinyint(1) not null,
	PRIMARY KEY(ID)
);

alter table PREGUNTAS_BINARIAS add constraint PREB$PRE foreign key (ID) references PREGUNTAS(ID);

create table PREGUNTAS_MULTIPLES(
	ID bigint not null,
	PRIMARY KEY(ID)
);

alter table PREGUNTAS_MULTIPLES add constraint PREM$PRE foreign key (ID) references PREGUNTAS(ID);

create table RESPUESTAS(
	ID bigint not null AUTO_INCREMENT,
	TEXTO varchar(255) not null,
	CORRECTA tinyint(1) null,
	OPCION varchar(5) not null,
	PREGUNTA_ID bigint not null,
	PRIMARY KEY(ID)
);

alter table RESPUESTAS add constraint RES$PREM foreign key (PREGUNTA_ID) references PREGUNTAS_MULTIPLES(ID);
