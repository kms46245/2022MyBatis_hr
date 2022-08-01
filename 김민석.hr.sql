drop user hr2 cascade;

create user hr2 identified by hr2 default tablespace users;
grant connect, resource to hr2;

create table hr2.laborers(
    laborer_id number(3),
    laborer_name varchar2(20),
    hire_date date);
    
alter table hr2.laborers
    add constraint lab_labId_pk primary key(laborer_id);

create sequence hr2.lab_labid_seq
    start with 1        
    increment by 1       
    maxvalue 999         
    nocache             
    nocycle;