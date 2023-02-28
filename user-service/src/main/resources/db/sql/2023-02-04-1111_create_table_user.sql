CREATE TABLE public."user" (
    user_      char(36) DEFAULT uuid_generate_v4() NOT NULL,
    user_name  varchar(255)                        NOT NULL,
    password   varchar(255)                        NOT NULL,
    first_name varchar(50)                         NULL,
    last_name  varchar(50)                         NULL,
    email      varchar(255)                        NOT NULL,
    enabled    bool                                NOT NULL,
    created    timestamp                           NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_),
    CONSTRAINT user_user_name_uk UNIQUE (user_name)
);