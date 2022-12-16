--liquibase formatted sql

-- changeset adianati:v9-1
CREATE TABLE IF NOT EXISTS dimension_category_entity(
    id BIGSERIAL PRIMARY KEY,
    descriptor VARCHAR(255) UNIQUE
);

-- changeset adianati:v9-2
CREATE TABLE IF NOT EXISTS question_entity(
    id BIGSERIAL PRIMARY KEY,
    category_id BIGSERIAL REFERENCES dimension_category_entity(id),
    question_text VARCHAR
);

-- changeset adianati:v9-3
INSERT INTO dimension_category_entity (descriptor) VALUES
    ('Projekt'),
    ('Alter'),
    ('Geschlechtliche Identität'),
    ('Ethnische Herkunft'),
    ('Religion'),
    ('Muttersprache'),
    ('Hobby'),
    ('Bildungsweg'),
    ('Berufserfahrung'),
    ('Ernährung'),
    ('Soziale Herkunft'),
    ('Diskriminierung aufgrund sozialer Herkunft'),
    ('Sexuelle Orientierung');

-- changeset adianati:v9-4
WITH
    proje_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Projekt' LIMIT 1),
    alter_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Alter' LIMIT 1),
    gesch_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Geschlechtliche Identität' LIMIT 1),
    ethni_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Ethnische Herkunft' LIMIT 1),
    relig_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Religion' LIMIT 1),
    mutte_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Muttersprache' LIMIT 1),
    hobby_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Hobby' LIMIT 1),
    bildu_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Bildungsweg' LIMIT 1),
    beruf_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Berufserfahrung' LIMIT 1),
    ernae_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Ernährung' LIMIT 1),
    sozia_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Soziale Herkunft' LIMIT 1),
    discr_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Diskriminierung aufgrund sozialer Herkunft' LIMIT 1),
    sexue_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Sexuelle Orientierung' LIMIT 1)
INSERT INTO question_entity (category_id, question_text) VALUES
    ((SELECT * FROM proje_id), 'Diversity matters! Inwiefern spielt gesellschaftliche Vielfalt an eurem Arbeitsplatz eine Rolle? Und in welchen Bereichen vermisst ihr bei der BROCKHAUS AG oder in eurem Team mehr Vielfalt?'),
    ((SELECT * FROM proje_id), 'Könntet ihr euch vorstellen mit einer Person mit Behinderung zusammen in einem Team zu arbeiten? Welche Chancen und Herausforderungen seht ihr dabei für euch?'),
    ((SELECT * FROM alter_id), 'Was denkt ihr: Sind altersgemischte Teams innovativer und leistungsfähiger als altershomogene Arbeitsgruppen?'),
    ((SELECT * FROM alter_id), 'Was könnt ihr von einem deutlich jüngeren oder älteren Kollegen_in lernen?'),
    ((SELECT * FROM alter_id), 'Welche Rolle spielt für euch Alter am Arbeitsplatz?'),
    ((SELECT * FROM alter_id), 'In welchen Situationen ist das Alter im Team von Bedeutung? Wann ist es unwichtig?'),
    ((SELECT * FROM gesch_id), 'Was denkt ihr: Wieso begeistern sich so wenige Frauen für die IT? Wo liegen vielleicht Stellschrauben?'),
    ((SELECT * FROM gesch_id), 'Wie unterschiedlich ticken Männer und Frauen?'),
    ((SELECT * FROM gesch_id), 'Sind einige Geschlechter deutlich unterrepräsentiert, werden die entsprechenden Mitarbeiter_innen oft unbewusst ausgegrenzt, besonders behandelt oder nicht mitgedacht und berücksichtigt. Ist euch das in eurem Arbeitsalltag schon einmal aufgefallen?'),
    ((SELECT * FROM gesch_id), 'Glaub ihr, dass es einen Unterscheid macht, wenn auf eine gendersensible Sprache im Arbeitsalltag geachtet wird? Wenn ja, welche? Wenn nicht, warum?'),
    ((SELECT * FROM gesch_id), 'Was sind aus eurer Sicht Gründe dafür, dass Frauen weniger stark als Männer in Führungspositionen vertreten sind? Wie findet ihr das?'),
    ((SELECT * FROM ethni_id), 'Aus welchem Land stammt ihr und welche kulturellen Unterschiede, aber auch welche Gemeinsamkeiten teilt ihr?'),
    ((SELECT * FROM ethni_id), 'Welche Probleme bringt ein Migrationshintergrund mit sich, wann wurdet ihr auf eure Herkunft reduziert oder genießt ihr dadurch Vorteile?'),
    ((SELECT * FROM ethni_id), 'In welchen beruflichen Situationen ist ein interkulturelles Team eurer Meinung nach besonders wichtig?'),
    ((SELECT * FROM relig_id), 'Unternehmen setzen sich vermehrt für einen respektvollen und wertschätzenden Umgang im Hinblick auf Religionszugehörigkeit und Weltanschauung ein. Typische Beispiele sind die Berücksichtigung der Feiertage von unterschiedlichen Religionen, die Einrichtung eines „Raums der Stille“ sowie Speisenangebote entsprechend der religiösen Gepflogenheiten. Fühlt ihr euch mit eurem Glauben bei der BROCKHAUS AG gut aufgehoben?'),
    ((SELECT * FROM relig_id), 'Was unterscheidet eure Religionen voneinander?'),
    ((SELECT * FROM relig_id), 'Welche Feiertage sind euch besonders wichtig und wie feiert ihr sie?'),
    ((SELECT * FROM relig_id), 'Welche Vorteile und welche Herausforderungen ergeben sich in einem Team durch die verschiedene Religionszugehörigkeiten der Beschäftigten?'),
    ((SELECT * FROM mutte_id), 'Welche Sprache ist eure Muttersprache, wurdet ihr vielleicht sogar zweisprachig erzogen? '),
    ((SELECT * FROM mutte_id), 'Was macht Mehrsprachigkeit mit Menschen, Öffentlichkeiten, Institutionen und Mitarbeiter*innen? '),
    ((SELECT * FROM mutte_id), 'Was sagt die Anzahl der gesprochenen Sprachen über einen Menschen aus?'),
    ((SELECT * FROM hobby_id), 'Gibt es Hobbys, die ihr teilt?'),
    ((SELECT * FROM hobby_id), 'Bei welchem Hobby könnt ihr so richtig abschalten?'),
    ((SELECT * FROM hobby_id), 'Welche Sportart/Welches Hobby wolltet ihr schon immer einmal ausprobieren?'),
    ((SELECT * FROM bildu_id), 'Wie sah euer Bildungsweg aus und was hat euren Bildungsweg beeinflusst? '),
    ((SELECT * FROM bildu_id), 'Seid ihr der Meinung, dass die soziale Herkunft eines Menschen seinen beruflichen Werdegang beeinflusst? Wenn ja, warum? Wenn nicht, warum nicht?'),
    ((SELECT * FROM beruf_id), 'Wie lange seid ihr schon in eurem Beruf tätig?'),
    ((SELECT * FROM beruf_id), 'Wenn ihr dieses mögliche Gesprächsthema ausgeliefert bekommen habt, ist es sehr wahrscheinlich so, dass einer von euch bereits viele Jahre Berufserfahrung vorzuweisen hat, der/die andere Gesprächsteilnehmende nicht. An Zweitere*n: Was kannst du von deinem Teammitglied lernen?'),
    ((SELECT * FROM beruf_id), 'Habt ihr das Mentoren-Programm in Anspruch genommen und wenn ja, wie hat euch das geholfen?'),
    ((SELECT * FROM ernae_id), 'Kocht ihr gerne und wenn ja, was? '),
    ((SELECT * FROM ernae_id), 'Was esst ihr während eures Gesprächs in der Mittagspause? Wieso ist die Wahl darauf gefallen? '),
    ((SELECT * FROM ernae_id), 'Was ist euer Lieblingsessen? '),
    ((SELECT * FROM ernae_id), 'Team Frisch oder Team Tiefkühlpizza? '),
    ((SELECT * FROM sexue_id), 'Welche Gemeinsamkeiten könnt ihr in euren Beziehungen feststellen?'),
    ((SELECT * FROM sexue_id), 'Darf man euch nach eurer sexuellen Orientierung fragen und wenn ja, wie?'),
    ((SELECT * FROM sexue_id), 'Niemand darf in Deutschland wegen seiner/ihrer sexuellen Orientierung benachteiligt werden. Hattet ihr trotzdem schon einmal das Gefühl, dass ihr wegen eurer sexuellen Orientierung diskriminiert wurdet oder Vorurteilen ausgesetzt seid?'),
    ((SELECT * FROM sozia_id), 'Haben alle Menschen die gleichen Chancen, wenn sie auf die Welt kommen? '),
    ((SELECT * FROM sozia_id), 'Habt ihr schon einmal Feindseligkeiten aufgrund sozialer Herkunft beobachtet oder erlebt?'),
    ((SELECT * FROM sozia_id), 'Welche Werte und welche Haltungen wurden euch durch eure soziale Herkunft vermittelt?'),
    ((SELECT * FROM discr_id), 'Welche sozialen Ungleichheiten bestehen eurer Meinung nach in der schulischen Bildung? '),
    ((SELECT * FROM discr_id), 'Wo gibt es überall soziale Ungleichheit?'),
    ((SELECT * FROM discr_id), 'Wie waren eure Werdegänge? Welchen Einfluss hatte eure Familie und der soziale Status auf eure Bildungschancen?');

-- changeset adianati:v9-5
ALTER TABLE meeting_entity DROP COLUMN question;

ALTER TABLE meeting_entity ADD COLUMN question_id BIGSERIAL REFERENCES question_entity(id);

WITH
    ernae_id AS (SELECT id FROM dimension_category_entity WHERE descriptor LIKE 'Ernährung' LIMIT 1)
UPDATE meeting_entity SET question_id = (SELECT id FROM question_entity WHERE category_id = (SELECT * FROM ernae_id) LIMIT 1);