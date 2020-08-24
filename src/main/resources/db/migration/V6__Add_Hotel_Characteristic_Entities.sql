insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table hotel_characteristic (id bigint not null, value varchar(255), hotel_id bigint, group_id bigint, primary key (id)) engine=InnoDB;
create table hotel_characteristic_groups (id bigint not null, name varchar(255), slug varchar(255), primary key (id)) engine=InnoDB;
alter table hotel_characteristic add constraint FKry5oxnmslyi8ttqvpa5bxpoyt foreign key (hotel_id) references hotels (id);
alter table hotel_characteristic add constraint FK9qoyni15pshiy8bm2hfn4li5u foreign key (group_id) references hotel_characteristic_groups (id);
