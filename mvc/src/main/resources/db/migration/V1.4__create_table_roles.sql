CREATE TABLE roles (
                       id                   BIGSERIAL NOT NULL,
                       name                 VARCHAR(30) not null unique,
                       allowed_resource     VARCHAR(200),
                       allowed_read         boolean not null default false,
                       allowed_create       boolean not null default false,
                       allowed_update       boolean not null default false,
                       allowed_delete       boolean not null default false
);

ALTER TABLE roles ADD CONSTRAINT role_pk PRIMARY KEY ( id );

CREATE TABLE customer_role (
                               customer_id    bigint NOT NULL,
                               role_id    bigint NOT NULL
);


ALTER TABLE customer_role
    ADD CONSTRAINT customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customers ( id );

ALTER TABLE customer_role
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES roles ( id );