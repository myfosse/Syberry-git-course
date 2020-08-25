create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table user (id bigint not null, email varchar(255), password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;