alter table RESPUESTAS drop constraint RES$PREM;

drop table RESPUESTAS;

alter table PREGUNTAS_MULTIPLES drop constraint PREM$PRE;

drop table PREGUNTAS_MULTIPLES;

alter table PREGUNTAS_BINARIAS drop constraint PREB$PRE;

drop table PREGUNTAS_BINARIAS;

drop table PREGUNTAS;

alter table USUARIOS drop constraint USR$IMG;

drop table USUARIOS;

drop table IMAGENES;