CREATE TABLE public.vendor (
    vendor_       char(36) DEFAULT uuid_generate_v4() NOT NULL,
    type          varchar(100)                        NOT NULL,
    display_name  varchar(255)                        NOT NULL,
    driver        varchar(100)                        NOT NULL,
    is_rdbms      boolean                             NOT NULL,
    jdbc_name     varchar(100),
    added_at      timestamp without time zone DEFAULT (now()):: timestamp (0) without time zone NOT NULL,
    CONSTRAINT vendor_pkey PRIMARY KEY (vendor_),
    CONSTRAINT if_is_rdbms_then_jdbc_name_is_not_null CHECK ((NOT is_rdbms) OR (jdbc_name IS NOT NULL))
);