--changeset Jabdelhafidh:17
ALTER TABLE account_entity RENAME COLUMN unique_name to oid;
ALTER TABLE account_entity ADD CONSTRAINT unique_oid UNIQUE (oid);