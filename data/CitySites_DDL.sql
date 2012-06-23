drop table if exists citysites;
create table citysites (id varchar(255) not null, latitude double precision, longitude double precision, site_name varchar(255), site_type varchar(255), primary key (id));
