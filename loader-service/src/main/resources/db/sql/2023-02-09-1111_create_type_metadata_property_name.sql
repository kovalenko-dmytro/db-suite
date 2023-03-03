CREATE TYPE metadata_property_name AS ENUM (
'name',
'version',
'uuid',
'collation',
'default_schema',
'is_system',
'extension_owner_id',
'extension_namespace_id',
'extension_is_relocatable',
'extension_version',
'is_typed_table',
'is_updatable',
'text',
'has_indexes',
'is_aggregate',
'is_trigger_function',
'return_type',
'is_udt',
'type_name',
'start_value',
'last_value',
'minimum_value',
'character_maximum_length',
'increment',
'numeric_precision',
'numeric_scale',
'cycle_option',
'cache_flag',
'cache_size',
'udt_name',
'udt_schema',
'domain_schema',
'allow_nulls',
'is_domain',
'is_type_enum',
'partition_type',
'partition_columns',
'type_name_alias',
'is_identity',
'is_nullable',
'is_generated',
'identity_option',
'column_default',
'ordinal_position',
'collation_name',
'index_is_ready',
'index_type',
'is_clustered',
'is_function_based',
'is_unique',
'is_valid',
'r_options',
'order_column',
'order_by',
'nulls',
'is_disabled',
'before_after_instead_of',
'granularity',
'trigger_event',
'referenced_database',
'table_schema',
'table_name',
'constraint_schema',
'constraint_type',
'is_deferrable',
'is_initially_deferred',
'constraint_type_desc',
'check_clause',
'referenced_table_schema',
'referenced_table_name',
'referenced_constraint_schema',
'referenced_constraint_name',
'match_option',
'update_rule',
'delete_rule',
'position_in_unique_constraint',
'column_in_unique_constraint');