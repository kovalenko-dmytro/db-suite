CREATE TABLE public.db_connection (
    connection_               char(36) DEFAULT uuid_generate_v4() NOT NULL,
    vendor_                   char(36)                            NOT NULL,
    connection_name           varchar(100)                        NOT NULL,
    host                      varchar(255)                        NOT NULL,
    port                      varchar(10)                         NOT NULL,
    database                  varchar(100)                        NOT NULL,
    username                  varchar(100)                        NOT NULL,
    password                  varchar(255)                        NOT NULL,
    verify_server_certificate boolean                             NOT NULL,
    use_ssl                   boolean                             NOT NULL,
    require_ssl               boolean                             NOT NULL,
    verified                  boolean                             NOT NULL,
    added_at                  timestamp without time zone DEFAULT (now()):: timestamp (0) without time zone NOT NULL,
    CONSTRAINT connection_pkey PRIMARY KEY (connection_),
    CONSTRAINT vendor_fkey FOREIGN KEY (vendor_) REFERENCES public.vendor (vendor_),
    CONSTRAINT connection_vendor_host_port_database_ukey UNIQUE (vendor_, host, port, database)
);