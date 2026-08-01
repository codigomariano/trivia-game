create table ROLES(
	ID int NOT NULL,
	NOMBRE varchar(50) NOT NULL,
	PRIMARY KEY(ID)
);

create table ROLES_USUARIOS(
	ROL_ID int not null,
	USUARIO_ID bigint not null,
	PRIMARY KEY(ROL_ID, USUARIO_ID)
);

alter table ROLES_USUARIOS add constraint RUSR$ROL foreign key (ROL_ID) references ROLES(ID);

alter table ROLES_USUARIOS add constraint RUSR$USR foreign key (USUARIO_ID) references USUARIOS(ID);
