create table auth (id bigint not null, refresh_token varchar(255), remember_me bit not null, user_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table users (id bigint not null, email varchar(255), password varchar(255), remember_me bit not null, username varchar(255), primary key (id)) engine=InnoDB;
alter table auth add constraint FKpv45mvdt7km1gvrtn5f9rsvd7 foreign key (user_id) references users (id);
