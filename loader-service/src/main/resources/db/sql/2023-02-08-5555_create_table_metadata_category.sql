CREATE TABLE public.metadata_category (
    metadata_category_  char(36) DEFAULT uuid_generate_v4() NOT NULL,
    type                metadata_category_type              NOT NULL,
    root                boolean                             NOT NULL,
    vendor_type         vendor_type                         NOT NULL,
    parent_             char(36)                            NOT NULL,
    version_from        varchar(10) DEFAULT '0.0'           NOT NULL,
    added_at            timestamp without time zone DEFAULT (now()):: timestamp (0) without time zone NOT NULL,
    CONSTRAINT metadata_category_pkey PRIMARY KEY (metadata_category_),
    CONSTRAINT metadata_category_fkey FOREIGN KEY (parent_) REFERENCES metadata_category (metadata_category_),
    CONSTRAINT metadata_category_type_vendor_type_parent_ukey UNIQUE (type, vendor_type, parent_)
);