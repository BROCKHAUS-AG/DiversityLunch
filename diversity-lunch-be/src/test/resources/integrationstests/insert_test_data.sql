INSERT INTO country_entity(descriptor) VALUES ('Algerien'),('Andorra'),('Angola'),('Antiguaundbarbuda'),('Argentinien'),('Armenien'),('Aserbaidschan'),('Australien'),('Bahamas'),('Bahrain'),('Bangladesch'),('Barbados'),('Belgien'),('Belize'),('Benin'),('Bhutan'),('Bolivien'),('Botswana'),('Botwana'),('Brasilien'),('Brunei'),('Bulgarien'),('Burkinafaso'),('Burundi'),('Chile'),('Chinarepubliktaiwan'),('Costarica'),('Deutschland'),('Dominica'),('Dominikanischerepublik'),('Dschibuti'),('Danemark'),('Ecuador'),('Elsalvador'),('Elfenbeinkuste'),('Eritrea'),('Estland'),('Eswatini'),('Finnland'),('Frankreich'),('Faroer'),('Gabun'),('Gambia'),('Georgien'),('Ghana'),('Ginea'),('Gineabissau'),('Grenada'),('Griechenland'),('Gronland'),('Guatemala'),('Guyana'),('Haiti'),('Honduras'),('Hongkong'),('Indien'),('Indonesien'),('Irak'),('Iran'),('Irland'),('Island'),('Israel'),('Italien'),('Jamaika'),('Japan'),('Jemen'),('Jordanien'),('Kambodscha'),('Kamerun'),('Kanada'),('Kapverde'),('Katar'),('Kenia'),('Kirgisistan'),('Kiribati'),('Kolumbien'),('Komoren'),('Kongo'),('Kosovo'),('Kroatien'),('Kuba'),('Kuwait'),('Laos'),('Lesotho'),('Lettland'),('Libanon'),('Liberia'),('Libyen'),('Liechtenstein'),('Litauen'),('Luxemburg'),('Macau'),('Madagaskar'),('Malawi'),('Malaysia'),('Malediven'),('Mali'),('Malta'),('Marokko'),('Mauretanien'),('Mauritius'),('Mexiko'),('Moldawien'),('Monaco'),('Mongolei'),('Mosambik'),('Motenegro'),('Myanmar'),('Namibia'),('Nepal'),('Neuseeland'),('Nicaragua'),('Niederlande'),('Niger'),('Nigeria'),('Nordkorea'),('Nordmazedonien'),('Norwegen'),('Oman'),('Pakistan'),('Palastina'),('Panama'),('Papuaneuguinea'),('Paraguay'),('Peru'),('Philippinen'),('Polen'),('Portugal'),('Republikmoldau'),('Ruanda'),('Rumanien'),('Russland'),('Sambia'),('Saudiarabien'),('Schweden'),('Schweiz'),('Senegal'),('Serbien'),('Seychellen'),('Sierraleone'),('Simbabwe'),('Singapur'),('Slowakei'),('Slowenien'),('Somalia'),('Spanien'),('Srilanka'),('Stkittsundnevis'),('Stlucia'),('Stvincentunddiegrenadinen'),('Sudan'),('Suriname'),('Syrien'),('Saotomeundprincipe'),('Sudafrika'),('Sudkorea'),('Sudsudan'),('Tadschikistan'),('Taiwan'),('Tansania'),('Thailand'),('Timorleste'),('Togo'),('Tonga'),('Trinidadundtobago'),('Tschad'),('Tschechien'),('Tunesien'),('Turkmenistan'),('Tuvalu'),('Turkei'),('Usa'),('Uganda'),('Ukraine'),('Ungarn'),('Uruguay'),('Usbekistan'),('Vatikanstadt'),('Venezuela'),('Vereinigtearabischeemirate'),('Vereinigteskonigreich'),('Vietnam'),('Volksrepublikchina'),('Wallisundfutuna'),('Weissrussland'),('Westsahara'),('Zentralafrikanischerepublik'),('Zypern'),('Agypten'),('Aquatorialguinea'),('Athiopien'),('Osterreich'),('Albanien'),('Bosnienundherzegowina'),('Kasachstan'),('Sanmarino');
INSERT INTO diet_entity(descriptor)  VALUES ('Fleischesser'),('Vegetarier'),('Veganer'), ('Sonstige');
INSERT INTO education_entity(descriptor) VALUES ('Ausbildung'), ('Studium'), ('Andere Wege');
INSERT INTO gender_entity(descriptor) VALUES ('Männlich'), ('Weiblich'), ('Keine Angabe');
INSERT INTO hobby_category_entity(descriptor) VALUES ('Sport'), ('Kreatives'), ('Natur'), ('Unterhaltung'), ('Kultur'), ('Sonstiges');
INSERT INTO language_entity(descriptor) VALUES ('Akan'),('Amharisch'),('Arabisch'),('Aserbaidschanisch'),('Assamesisch'),('Awadhi'),('Belutschisch'),('Bengalisch'),('Bhojpuri'),('Bulgarisch'),('Burmesisch'),('Cebuano'),('Chhattisgarhi'),('Chichewa'),('Chittagonisch'),('Dakhini'),('Deutsch'),('Dhundari'),('Daenisch'),('Englisch'),('Estnisch'),('Finnisch'),('Franzoesisch'),('Fulfulde'),('Ganchinesisch'),('Griechisch'),('Guarani'),('Gujarati'),('Haitanischeskreol'),('Hakkachinesisch'),('Haryanvi'),('Hausa'),('Hiligaynon'),('Hindi'),('Hmong'),('Hocharabisch'),('Igbo'),('Ilokano'),('Indonesisch'),('Irisch'),('Italienisch'),('Japanisch'),('Javanisch'),('Jinchinesisch'),('Kannada'),('Kantonesisch'),('Kasachisch'),('Katalanisch'),('Khmer'),('Kinyarwanda'),('Kirundi'),('Konkani'),('Koreanisch'),('Kroatisch'),('Kurdisch'),('Lettisch'),('Litauisch'),('Maduresisch'),('Magahi'),('Maithili'),('Malagasy'),('Malaiisch'),('Malayalam'),('Maltesisch'),('Marathi'),('Marwari'),('Minanchinesisch'),('Minbeichinesisch'),('Mindongchinesisch'),('Moore'),('Nahuatl'),('Nepali'),('Niederlaendisch'),('Oriya'),('Oromo'),('Panjabi'),('Paschtunisch'),('Persisch'),('Polnisch'),('Portugiesisch'),('Quechua'),('Rumaenisch'),('Russisch'),('Saraiki'),('Schwedisch'),('Sindhi'),('Singhalesisch'),('Slowakisch'),('Slowenisch'),('Somali'),('Spanisch'),('Suaheli'),('Sundanesisch'),('Sylheti'),('Tagalog'),('Tamil'),('Telugu'),('Thai'),('Tschechisch'),('Turkmenisch'),('Tuerkisch'),('Uigurisch'),('Ukrainisch'),('Ungarisch'),('Urdu'),('Usbekisch'),('Vietnamesisch'),('Weissrussisch'),('Wuchinesisch'),('Xiangchinesisch'),('Yoruba'),('Zhuangchinesisch'),('Isixhosa'),('Isizulu'),('Aegyptischarabisch'),('Mandarinchinesisch');
INSERT INTO project_entity(descriptor) VALUES ('Externes Projekt'), ('Internes Projekt');
INSERT INTO religion_entity(descriptor) VALUES ('Judentum'),('Christentum'),('Islam'),('Hinduismus'),('Buddhismus'),('Sonstige'),('Kein Glaube');
INSERT INTO work_experience_entity(descriptor, weight) VALUES ('0-3 Jahre', 1), ('4-10 Jahre', 2),('über 10 Jahre', 3);

WITH sport AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Sport' LIMIT 1),
     kreatives AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Kreatives' LIMIT 1),
     natur AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Natur' LIMIT 1),
     unterhaltung AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Unterhaltung' LIMIT 1),
     kultur AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Kultur' LIMIT 1),
     sonstiges AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Sonstiges' LIMIT 1)

INSERT INTO hobby_entity(descriptor, category_id) VALUES ('Angeln', (SELECT * FROM natur)), ('Ballsport', (SELECT * FROM sport)), ('Bogenschießen', (SELECT * FROM sport)), ('Camping', (SELECT * FROM natur)),
                                                         ('Dart', (SELECT * FROM sport)), ('DIY', (SELECT * FROM kreatives)), ('Filme', (SELECT * FROM unterhaltung)), ('Gaming', (SELECT * FROM unterhaltung)),
                                                         ('Gärtnern', (SELECT * FROM natur)), ('Fotografie', (SELECT * FROM kreatives)), ('Gesellschaftsspiele', (SELECT * FROM kultur)),
                                                         ('Jagen', (SELECT * FROM natur)), ('Joggen', (SELECT * FROM sport)), ('Kampfsport', (SELECT * FROM sport)), ('Klettern', (SELECT * FROM sport)),
                                                         ('Kochen', (SELECT * FROM kultur)), ('Lesen', (SELECT * FROM unterhaltung)), ('Malen', (SELECT * FROM kreatives)), ('Musik', (SELECT * FROM unterhaltung)),
                                                         ('Puzzlen', (SELECT * FROM unterhaltung)), ('Radsport', (SELECT * FROM sport)), ('Tanzen', (SELECT * FROM sport)), ('Theater', (SELECT * FROM kultur)),
                                                         ('Wandern', (SELECT * FROM natur)), ('Yoga', (SELECT * FROM sport)), ('Sonstiges', (SELECT * FROM sonstiges));
INSERT INTO sexual_orientation_entity(descriptor) VALUES
                                                      ('Heterosexuell'),
                                                      ('Homosexuell'),
                                                      ('Bisexuell'),
                                                      ('Asexuell'),
                                                      ('Pansexuell'),
                                                      ('weitere Orientierungen'),
                                                      ('keine Angabe');
INSERT INTO social_background_entity(descriptor) VALUES ('Akademikerfamilie'), ('Nichtakademisches Elternhaus'), ('keine Angabe');
INSERT INTO social_background_discrimination_entity(descriptor) VALUES ('Ja'), ('Nein'), ('keine Angabe');

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
    ((SELECT * FROM proje_id), 'Könntet ihr euch vorstellen mit einer Person mit Behinderung zusammen in einem Team zu arbeiten? Welche Chancen und Herausforderungen seht ihr dabei für euch?'),
    ((SELECT * FROM alter_id), 'Was denkt ihr: Sind altersgemischte Teams innovativer und leistungsfähiger als altershomogene Arbeitsgruppen?'),
    ((SELECT * FROM alter_id), 'Was könnt ihr von einem deutlich jüngeren oder älteren Kollegen_in lernen?'),
    ((SELECT * FROM alter_id), 'Welche Rolle spielt für euch Alter am Arbeitsplatz?'),
    ((SELECT * FROM alter_id), 'In welchen Situationen ist das Alter im Team von Bedeutung? Wann ist es unwichtig?'),
    ((SELECT * FROM gesch_id), 'Was denkt ihr: Wieso begeistern sich so wenige Frauen für die IT? Wo liegen vielleicht Stellschrauben?'),
    ((SELECT * FROM gesch_id), 'Wie unterschiedlich ticken Männer und Frauen?'),
    ((SELECT * FROM gesch_id), 'Glaub ihr, dass es einen Unterscheid macht, wenn auf eine gendersensible Sprache im Arbeitsalltag geachtet wird? Wenn ja, welche? Wenn nicht, warum?'),
    ((SELECT * FROM gesch_id), 'Was sind aus eurer Sicht Gründe dafür, dass Frauen weniger stark als Männer in Führungspositionen vertreten sind? Wie findet ihr das?'),
    ((SELECT * FROM ethni_id), 'Aus welchem Land stammt ihr und welche kulturellen Unterschiede, aber auch welche Gemeinsamkeiten teilt ihr?'),
    ((SELECT * FROM ethni_id), 'Welche Probleme bringt ein Migrationshintergrund mit sich, wann wurdet ihr auf eure Herkunft reduziert oder genießt ihr dadurch Vorteile?'),
    ((SELECT * FROM ethni_id), 'In welchen beruflichen Situationen ist ein interkulturelles Team eurer Meinung nach besonders wichtig?'),
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
    ((SELECT * FROM beruf_id), 'Wie lange seid ihr schon in eurem Beruf tätig?'),
    ((SELECT * FROM beruf_id), 'Habt ihr das Mentoren-Programm in Anspruch genommen und wenn ja, wie hat euch das geholfen?'),
    ((SELECT * FROM ernae_id), 'Kocht ihr gerne und wenn ja, was? '),
    ((SELECT * FROM ernae_id), 'Was esst ihr während eures Gesprächs in der Mittagspause? Wieso ist die Wahl darauf gefallen? '),
    ((SELECT * FROM ernae_id), 'Was ist euer Lieblingsessen? '),
    ((SELECT * FROM ernae_id), 'Team Frisch oder Team Tiefkühlpizza? '),
    ((SELECT * FROM sexue_id), 'Welche Gemeinsamkeiten könnt ihr in euren Beziehungen feststellen?'),
    ((SELECT * FROM sexue_id), 'Darf man euch nach eurer sexuellen Orientierung fragen und wenn ja, wie?'),
    ((SELECT * FROM sozia_id), 'Haben alle Menschen die gleichen Chancen, wenn sie auf die Welt kommen? '),
    ((SELECT * FROM sozia_id), 'Habt ihr schon einmal Feindseligkeiten aufgrund sozialer Herkunft beobachtet oder erlebt?'),
    ((SELECT * FROM sozia_id), 'Welche Werte und welche Haltungen wurden euch durch eure soziale Herkunft vermittelt?'),
    ((SELECT * FROM discr_id), 'Welche sozialen Ungleichheiten bestehen eurer Meinung nach in der schulischen Bildung? '),
    ((SELECT * FROM discr_id), 'Wo gibt es überall soziale Ungleichheit?'),
    ((SELECT * FROM discr_id), 'Wie waren eure Werdegänge? Welchen Einfluss hatte eure Familie und der soziale Status auf eure Bildungschancen?');
