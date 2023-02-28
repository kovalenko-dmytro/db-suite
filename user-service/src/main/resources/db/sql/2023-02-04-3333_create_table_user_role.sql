CREATE TABLE public.user_role (
    user_role_ char(36) DEFAULT uuid_generate_v4() NOT NULL,
    user_      char(36)                            NOT NULL,
    role_      char(36)                            NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_role_),
    CONSTRAINT user_role_uk UNIQUE (user_, role_),
    CONSTRAINT user_role_user_fkey FOREIGN KEY (user_) REFERENCES public."user" (user_),
    CONSTRAINT user_role_role_fkey FOREIGN KEY (role_) REFERENCES public."role" (role_)
);