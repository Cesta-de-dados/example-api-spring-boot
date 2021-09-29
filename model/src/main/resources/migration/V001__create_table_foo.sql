--liquibase formatted sql

--changeset roberto.aoki:001

create sequence foo_sequence;
--rollback drop sequence foo_sequence cascade;

create table foo(
    id int not null primary key default nextval('foo_sequence'),
    name text not null
)
--rollback drop table foo cascade;