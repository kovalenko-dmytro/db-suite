CREATE TABLE public.metadata (
    metadata_           char(36) DEFAULT uuid_generate_v4() NOT NULL,
    connection_         char(36)                            NOT NULL,
    type                metadata_type                       NOT NULL,
    metadata_category_  char(36)                            NOT NULL,
    parent_             char(36),
    added_at            timestamp without time zone DEFAULT (now()):: timestamp (0) without time zone NOT NULL,
    CONSTRAINT metadata_pkey PRIMARY KEY (metadata_),
    CONSTRAINT metadata_metadata_category_fkey FOREIGN KEY (metadata_category_) REFERENCES metadata_category (metadata_category_),
    CONSTRAINT metadata_parent_fkey FOREIGN KEY (parent_) REFERENCES metadata (metadata_)
);