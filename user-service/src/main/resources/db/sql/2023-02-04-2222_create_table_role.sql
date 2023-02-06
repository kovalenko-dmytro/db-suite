CREATE TABLE public."role" (
    role_ char(36) DEFAULT uuid_generate_v4() NOT NULL,
    role  varchar(255)                        NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_)
);