--liquibase formatted sql

--changeset roberto.aoki:001

create sequence bar_sequence;
--rollback drop sequence bar_sequence cascade;

create table bar(
    id int not null primary key default nextval('bar_sequence'),
    name text not null,
    foo_id int not null,
    CONSTRAINT fk_foo
        FOREIGN KEY(foo_id)
            REFERENCES foo(id)
)
--rollback drop table bar cascade;