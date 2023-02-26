CREATE TYPE metadata_type AS ENUM (
    'SERVER',
    'DATABASE',
    'SCHEMA',
    'TABLE',
    'COLUMN',
    'INDEX',
    'PRIMARY_KEY',
    'UNIQUE_KEY',
    'FOREIGN_KEY',
    'CHECK_CONSTRAINT',
    'TRIGGER',
    'VIEW',
    'PROCEDURE',
    'FUNCTION',
    'AGGREGATE_FUNCTION',
    'PARAMETER',
    'SYNONYM',
    'PACKAGE',
    'SEQUENCE',
    'RULE',
    'DEFAULT',
    'USER_DEFINED_TYPE',
    'ASSEMBLY',
    'EXTENSION',
    'SORT_KEY',
    'DISTRIBUTION_KEY',
    'PARTITION',
    'JOIN_INDEX',
    'MACROS',
    'OPERATOR',
    'EXTERNAL_TABLE',
    'SHARED_LIBRARY',
    'FLEX_TABLE',
    'FLEX_EXTERNAL_TABLE',
    'PROJECTION',
    'TEXT_INDEX',
    'UDT_ENUM',
    'MATERIALIZED_VIEW',
    'VIRTUAL_VIEW'
);