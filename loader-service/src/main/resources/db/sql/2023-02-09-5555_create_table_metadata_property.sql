CREATE TABLE public.metadata_property (
    metadata_property_  char(36) DEFAULT uuid_generate_v4() NOT NULL,
    property_name       metadata_property_name              NOT NULL,
    property_value      text,
    metadata_           char(36)                            NOT NULL,
    added_at            timestamp without time zone DEFAULT (now()):: timestamp (0) without time zone NOT NULL,
    CONSTRAINT metadata_property_pkey PRIMARY KEY (metadata_property_),
    CONSTRAINT metadata_property_fkey FOREIGN KEY (metadata_) REFERENCES metadata (metadata_),
    CONSTRAINT metadata_property_property_name_metadata_ukey UNIQUE (property_name, metadata_)
);