CREATE TABLE public.book (
    book_    bigserial NOT NULL,
    added_at timestamp    NOT NULL,
    author   varchar(255) NOT NULL,
    title    varchar(255) NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (book_)
);