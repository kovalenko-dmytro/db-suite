CREATE TABLE public.metadata_category_metadata_category (
    guid_              char(36) DEFAULT uuid_generate_v4() NOT NULL,
    metadata_category_ char(36)                            NOT NULL,
    parent_            char(36)                            NOT NULL,
    CONSTRAINT metadata_category_metadata_category_pkey PRIMARY KEY (guid_),
    CONSTRAINT metadata_category_metadata_category_uk UNIQUE (metadata_category_, parent_),
    CONSTRAINT metadata_category_fkey FOREIGN KEY (metadata_category_) REFERENCES public.metadata_category (metadata_category_),
    CONSTRAINT parent_fkey FOREIGN KEY (parent_) REFERENCES public.metadata_category (metadata_category_)
);