--changeset nvettivel:v7-1
INSERT INTO religion_entity(descriptor) VALUES ('Agnostiker');
INSERT INTO religion_entity(descriptor) VALUES ('Atheist');
Update religion_entity SET descriptor='keiner Konfession angehörig' WHERE descriptor='Kein Glaube';