drop table if exists citysites;
create table citysites (id varchar(255) not null, address varchar(255), address2 varchar(255), city varchar(255), live_feed varchar(255), latitude double precision, longitude double precision, site_name varchar(255), state varchar(255), site_type varchar(255), url varchar(255), zip varchar(255), primary key (id));
