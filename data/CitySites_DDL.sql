alter table reservations drop foreign key FKB7D3362743B400AC;
drop table if exists citysites;
drop table if exists reservations;
create table citysites (id varchar(255) not null, address varchar(255), address2 varchar(255), city varchar(255), site_desc longtext, live_feed varchar(255), latitude double precision, longitude double precision, site_name varchar(255), state varchar(255), site_type varchar(255), url varchar(255), zip varchar(255), primary key (id));
create table reservations (id varchar(255) not null, email varchar(255), first_name varchar(255), last_name varchar(255), phone varchar(255), reservation_date date, citySite_id varchar(255), primary key (id));
alter table reservations add index FKB7D3362743B400AC (citySite_id), add constraint FKB7D3362743B400AC foreign key (citySite_id) references citysites (id);
