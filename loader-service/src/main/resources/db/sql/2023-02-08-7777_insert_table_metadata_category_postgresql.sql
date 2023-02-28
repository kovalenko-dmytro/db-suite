INSERT INTO metadata_category (type, root, vendor) VALUES ('Servers', true, 'PostgreSql');

INSERT INTO metadata_category (type, root, vendor) VALUES ('Databases', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Databases'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Servers'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Schemas', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Databases'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Extensions', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Extensions'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Databases'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Tables', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Views', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Views'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Materialized views', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Materialized views'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Procedures', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Procedures'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Functions', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Functions'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Aggregate functions', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Aggregate functions'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Sequences', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Sequences'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('User defined types', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'User defined types'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Schemas'));

INSERT INTO metadata_category (type, root, vendor, version_from) VALUES ('Partitions', false, 'PostgreSql', '10.0');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Partitions'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Columns', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Columns'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Columns'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Views'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Columns'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Materialized views'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Indexes', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Indexes'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Indexes'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Materialized views'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Index columns', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Index columns'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Indexes'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Triggers', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Triggers'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Triggers'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Views'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Primary keys', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Primary keys'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Foreign keys', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Foreign keys'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Unique keys', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Unique keys'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Check constraints', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Check constraints'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Tables'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Check constraints'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'User defined types'));

INSERT INTO metadata_category (type, root, vendor) VALUES ('Parameters', false, 'PostgreSql');
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Parameters'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Procedures'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Parameters'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Functions'));
INSERT INTO metadata_category_metadata_category (metadata_category_, parent_) VALUES ((SELECT metadata_category_ FROM metadata_category WHERE type = 'Parameters'), (SELECT metadata_category_ FROM metadata_category WHERE type = 'Aggregate functions'));

