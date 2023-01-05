INSERT INTO dimension_category (description) VALUES
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
    proje_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Projekt' LIMIT 1),
    alter_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Alter' LIMIT 1),
    gesch_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Geschlechtliche Identität' LIMIT 1),
    ethni_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Ethnische Herkunft' LIMIT 1),
    relig_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Religion' LIMIT 1),
    mutte_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Muttersprache' LIMIT 1),
    bildu_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Bildungsweg' LIMIT 1),
    ernae_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Ernährung' LIMIT 1),
    sozia_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Soziale Herkunft' LIMIT 1),
    discr_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Diskriminierung aufgrund sozialer Herkunft' LIMIT 1),
    sexue_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Sexuelle Orientierung' LIMIT 1)
INSERT INTO basic_dimension_selectable_option (ignore_in_scoring, value, dimension_category_id) VALUES
    (false, 'Algerien', ethni_id), (false, 'Andorra', ethni_id), (false, 'Angola', ethni_id), (false, 'Antiguaundbarbuda', ethni_id), (false, 'Argentinien', ethni_id), (false, 'Armenien', ethni_id), (false, 'Aserbaidschan', ethni_id), (false, 'Australien', ethni_id), (false, 'Bahamas', ethni_id), (false, 'Bahrain', ethni_id), (false, 'Bangladesch', ethni_id), (false, 'Barbados', ethni_id), (false, 'Belgien', ethni_id), (false, 'Belize', ethni_id), (false, 'Benin', ethni_id), (false, 'Bhutan', ethni_id), (false, 'Bolivien', ethni_id), (false, 'Botswana', ethni_id), (false, 'Botwana', ethni_id), (false, 'Brasilien', ethni_id), (false, 'Brunei', ethni_id), (false, 'Bulgarien', ethni_id), (false, 'Burkinafaso', ethni_id), (false, 'Burundi', ethni_id), (false, 'Chile', ethni_id), (false, 'Chinarepubliktaiwan', ethni_id), (false, 'Costarica', ethni_id), (false, 'Deutschland', ethni_id), (false, 'Dominica', ethni_id), (false, 'Dominikanischerepublik', ethni_id), (false, 'Dschibuti', ethni_id), (false, 'Danemark', ethni_id), (false, 'Ecuador', ethni_id), (false, 'Elsalvador', ethni_id), (false, 'Elfenbeinkuste', ethni_id), (false, 'Eritrea', ethni_id), (false, 'Estland', ethni_id), (false, 'Eswatini', ethni_id), (false, 'Finnland', ethni_id), (false, 'Frankreich', ethni_id), (false, 'Faroer', ethni_id), (false, 'Gabun', ethni_id), (false, 'Gambia', ethni_id), (false, 'Georgien', ethni_id), (false, 'Ghana', ethni_id), (false, 'Ginea', ethni_id), (false, 'Gineabissau', ethni_id), (false, 'Grenada', ethni_id), (false, 'Griechenland', ethni_id), (false, 'Gronland', ethni_id), (false, 'Guatemala', ethni_id), (false, 'Guyana', ethni_id), (false, 'Haiti', ethni_id), (false, 'Honduras', ethni_id), (false, 'Hongkong', ethni_id), (false, 'Indien', ethni_id), (false, 'Indonesien', ethni_id), (false, 'Irak', ethni_id), (false, 'Iran', ethni_id), (false, 'Irland', ethni_id), (false, 'Island', ethni_id), (false, 'Israel', ethni_id), (false, 'Italien', ethni_id), (false, 'Jamaika', ethni_id), (false, 'Japan', ethni_id), (false, 'Jemen', ethni_id), (false, 'Jordanien', ethni_id), (false, 'Kambodscha', ethni_id), (false, 'Kamerun', ethni_id), (false, 'Kanada', ethni_id), (false, 'Kapverde', ethni_id), (false, 'Katar', ethni_id), (false, 'Kenia', ethni_id), (false, 'Kirgisistan', ethni_id), (false, 'Kiribati', ethni_id), (false, 'Kolumbien', ethni_id), (false, 'Komoren', ethni_id), (false, 'Kongo', ethni_id), (false, 'Kosovo', ethni_id), (false, 'Kroatien', ethni_id), (false, 'Kuba', ethni_id), (false, 'Kuwait', ethni_id), (false, 'Laos', ethni_id), (false, 'Lesotho', ethni_id), (false, 'Lettland', ethni_id), (false, 'Libanon', ethni_id), (false, 'Liberia', ethni_id), (false, 'Libyen', ethni_id), (false, 'Liechtenstein', ethni_id), (false, 'Litauen', ethni_id), (false, 'Luxemburg', ethni_id), (false, 'Macau', ethni_id), (false, 'Madagaskar', ethni_id), (false, 'Malawi', ethni_id), (false, 'Malaysia', ethni_id), (false, 'Malediven', ethni_id),(false, 'Mali', ethni_id),(false, 'Malta', ethni_id), (false, 'Marokko', ethni_id), (false, 'Mauretanien', ethni_id), (false, 'Mauritius', ethni_id), (false, 'BotwMexiko', ethni_id), (false, 'Moldawien', ethni_id), (false, 'Monaco', ethni_id), (false, 'Mongolei', ethni_id), (false, 'Mosambik', ethni_id), (false, 'Motenegro', ethni_id), (false, 'Myanmar', ethni_id), (false, 'Namibia', ethni_id), (false, 'Nepal', ethni_id), (false, 'Neuseeland', ethni_id), (false, 'Nicaragua', ethni_id), (false, 'Niederlande', ethni_id), (false, 'Niger', ethni_id), (false, 'Nigeria', ethni_id), (false, 'Nordkorea', ethni_id), (false, 'Nordmazedonien', ethni_id), (false, 'Norwegen', ethni_id), (false, 'Oman', ethni_id), (false, 'Pakistan', ethni_id), (false, 'Palastina', ethni_id), (false, 'Panama', ethni_id), (false, 'Papuaneuguinea', ethni_id), (false, 'Paraguay', ethni_id), (false, 'Peru', ethni_id), (false, 'Philippinen', ethni_id), (false, 'Polen', ethni_id), (false, 'Portugal', ethni_id), (false, 'Republikmoldau', ethni_id), (false, 'Ruanda', ethni_id), (false, 'Rumanien', ethni_id), (false, 'Russland', ethni_id), (false, 'Sambia', ethni_id), (false, 'Saudiarabien', ethni_id), (false, 'Schweden', ethni_id), (false, 'Schweiz', ethni_id), (false, 'Senegal', ethni_id), (false, 'Serbien', ethni_id), (false, 'Seychellen', ethni_id), (false, 'Sierraleone', ethni_id), (false, 'Simbabwe', ethni_id), (false, 'Singapur', ethni_id), (false, 'Slowakei', ethni_id), (false, 'Slowenien', ethni_id), (false, 'Somalia', ethni_id), (false, 'Spanien', ethni_id), (false, 'Srilanka', ethni_id), (false, 'Stkittsundnevis', ethni_id),(false, 'Stlucia', ethni_id),(false, 'Stvincentunddiegrenadinen', ethni_id),(false, 'Sudan', ethni_id),(false, 'Suriname', ethni_id),(false, 'Syrien', ethni_id),(false, 'Saotomeundprincipe', ethni_id),(false, 'Sudafrika', ethni_id),(false, 'Sudkorea', ethni_id),(false, 'Sudsudan', ethni_id),(false, 'Tadschikistan', ethni_id),(false, 'Taiwan', ethni_id),(false, 'Tansania', ethni_id),(false, 'Thailand', ethni_id),(false, 'Timorleste', ethni_id),(false, 'Togo', ethni_id),(false, 'Tonga', ethni_id),(false, 'Trinidadundtobago', ethni_id),(false, 'Tschad', ethni_id),(false, 'Tschechien', ethni_id),(false, 'Tunesien', ethni_id),(false, 'Turkmenistan', ethni_id),(false, 'Tuvalu', ethni_id),(false, 'Turkei', ethni_id),(false, 'Usa', ethni_id),(false, 'Uganda', ethni_id),(false, 'Ukraine', ethni_id),(false, 'Ungarn', ethni_id),(false, 'Uruguay', ethni_id),(false, 'Usbekistan', ethni_id),(false, 'Vatikanstadt', ethni_id),(false, 'Venezuela', ethni_id),(false, 'Vereinigtearabischeemirate', ethni_id),(false, 'Vereinigteskonigreich', ethni_id),(false, 'Vietnam', ethni_id),(false, 'Volksrepublikchina', ethni_id),(false, 'Wallisundfutuna', ethni_id),(false, 'Weissrussland', ethni_id),(false, 'Westsahara', ethni_id),(false, 'Zentralafrikanischerepublik', ethni_id),(false, 'Zypern', ethni_id),(false, 'Agypten', ethni_id),(false, 'Aquatorialguinea', ethni_id),(false, 'Athiopien', ethni_id),(false, 'Osterreich', ethni_id),(false, 'Albanien', ethni_id),(false, 'Bosnienundherzegowina', ethni_id),(false, 'Kasachstan', ethni_id),(false, 'Sanmarino', ethni_id),
    (false, 'Fleischesser', ernae_id), (false, 'Vegetarier', ernae_id), (false, 'Veganer', ernae_id), (false, 'Sonstige', ernae_id),
    (false, 'Ausbildung', bildu_id), (false, 'Studium', false), (false, 'Andere Wege', bildu_id),
    (false, 'Männlich', true), (false, 'Weiblich', gesch_id), (false, 'Keine Angabe', gesch_id),
    (false, 'Akan', true),(false, 'Amharisch', false),(false, 'Arabisch', false),(false, 'Aserbaidschanisch', false),(false, 'Assamesisch', false),(false, 'Awadhi', false),(false, 'Belutschisch', false),(false, 'Bengalisch', false),(false, 'Bhojpuri', false),(false, 'Bulgarisch', false),(false, 'Burmesisch', false),(false, 'Cebuano', false),(false, 'Chhattisgarhi', false),(false, 'Chichewa', false),(false, 'Chittagonisch', false),(false, 'Dakhini', false),(false, 'Deutsch', false),(false, 'Dhundari', false),(false, 'Daenisch', false),(false, 'Englisch', false),(false, 'Estnisch', mutte_id),(false, 'Finnisch', mutte_id),(false, 'Franzoesisch', mutte_id),(false, 'Fulfulde', mutte_id),(false, 'Ganchinesisch', mutte_id),(false, 'Griechisch', mutte_id),(false, 'Guarani', mutte_id),(false, 'Gujarati', mutte_id),(false, 'Haitanischeskreol', mutte_id),(false, 'Hakkachinesisch', mutte_id),(false, 'Haryanvi', mutte_id),(false, 'Hausa', mutte_id),(false, 'Hiligaynon', mutte_id),(false, 'Hindi', mutte_id),(false, 'Hmong', mutte_id),(false, 'Hocharabisch', mutte_id),(false, 'Igbo', mutte_id),(false, 'Ilokano', mutte_id),(false, 'Indonesisch', mutte_id),(false, 'Irisch', mutte_id),(false, 'Italienisch', mutte_id),(false, 'Japanisch', mutte_id),(false, 'Javanisch', mutte_id),(false, 'Jinchinesisch', mutte_id),(false, 'Kannada', mutte_id),(false, 'Kantonesisch', mutte_id),(false, 'Kasachisch', mutte_id),(false, 'Katalanisch', mutte_id),(false, 'Khmer', mutte_id),(false, 'Kinyarwanda', mutte_id),(false, 'Kirundi', mutte_id),(false, 'Konkani', mutte_id),(false, 'Koreanisch', mutte_id),(false, 'Kroatisch', mutte_id),(false, 'Kurdisch', mutte_id),(false, 'Lettisch', mutte_id),(false, 'Litauisch', mutte_id),(false, 'Maduresisch', mutte_id),(false, 'Magahi', mutte_id),(false, 'Maithili', mutte_id),(false, 'Malagasy', mutte_id),(false, 'Malaiisch', mutte_id),(false, 'Malayalam', mutte_id),(false, 'Maltesisch', mutte_id),(false, 'Marathi', mutte_id),(false, 'Marwari', mutte_id),(false, 'Minanchinesisch', mutte_id),(false, 'Minbeichinesisch', mutte_id),(false, 'Mindongchinesisch', mutte_id),(false, 'Moore', mutte_id),(false, 'Nahuatl', mutte_id),(false, 'Nepali', mutte_id),(false, 'Niederlaendisch', mutte_id),(false, 'Oriya', mutte_id),(false, 'Oromo', mutte_id),(false, 'Panjabi', mutte_id),(false, 'Paschtunisch', mutte_id),(false, 'Persisch', mutte_id),(false, 'Polnisch', mutte_id),(false, 'Portugiesisch', mutte_id),(false, 'Quechua', mutte_id),(false, 'Rumaenisch', mutte_id),(false, 'Russisch', mutte_id),(false, 'Saraiki', mutte_id),(false, 'Schwedisch', mutte_id),(false, 'Sindhi', mutte_id),(false, 'Singhalesisch', mutte_id),(false, 'Slowakisch', mutte_id),(false, 'Slowenisch', mutte_id),(false, 'Somali', mutte_id),(false, 'Spanisch', mutte_id),(false, 'Suaheli', mutte_id),(false, 'Sundanesisch', mutte_id),(false, 'Sylheti', mutte_id),(false, 'Tagalog', mutte_id),(false, 'Tamil', mutte_id),(false, 'Telugu', mutte_id),(false, 'Thai', mutte_id),(false, 'Tschechisch', mutte_id),(false, 'Turkmenisch', mutte_id),(false, 'Tuerkisch', mutte_id),(false, 'Uigurisch', mutte_id),(false, 'Ukrainisch', mutte_id),(false, 'Ungarisch', mutte_id),(false, 'Urdu', mutte_id),(false, 'Usbekisch', mutte_id),(false, 'Vietnamesisch', mutte_id),(false, 'Weissrussisch', mutte_id),(false, 'Wuchinesisch', mutte_id),(false, 'Xiangchinesisch', mutte_id),(false, 'Yoruba', mutte_id),(false, 'Zhuangchinesisch', relig_id),('Isixhosa', mutte_id),(false, 'Isizulu', mutte_id),(false, 'Aegyptischarabisch', mutte_id),(false, 'Mandarinchinesisch', mutte_id),
    (false, 'Externes Projekt', true), (false, 'Internes Projekt', proje_id),
    (false, 'Judentum', true),(false, 'Christentum', false),(false, 'Islam', false),(false, 'Hinduismus', relig_id),('Buddhismus', relig_id),('Sonstige', relig_id),('Kein Glaube', relig_id),
    (false, 'Heterosexuell', sexue_id), (false, 'Homosexuell', sexue_id), (false, 'Bisexuell', sexue_id), (false, 'Asexuell', sexue_id), (false, 'Pansexuell', sexue_id), (false, 'weitere Orientierungen', sexue_id), (true, 'keine Angabe', sexue_id),
    (false, 'Akademikerfamilie', sozia_id), (false, 'Nichtakademisches Elternhaus', sozia_id), (true, 'keine Angabe', sozia_id),
    (false, 'Ja', discr_id), (false, 'Nein', discr_id), (true, 'keine Angabe', discr_id);

WITH beruf_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Berufserfahrung' LIMIT 1)
INSERT INTO weighted_dimension_selectable_option (ignore_in_scoring, value, weight, dimension_category_id) VALUES
    (false, '0-3 Jahre', 1, beruf_id), (false, '4-10 Jahre', 2, beruf_id),(false, 'über 10 Jahre', 3, beruf_id);

WITH hobby_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Hobby' LIMIT 1)
INSERT INTO multiselect_dimension_selectable_option (value, dimension_category_id) VALUES
    ('Angeln', hobby_id), ('Ballsport', hobby_id), ('Bogenschießen', hobby_id), ('Camping', hobby_id), ('Dart', hobby_id), ('DIY', hobby_id), ('Filme', hobby_id), ('Gaming', hobby_id), ('Gärtnern', hobby_id), ('Fotografie', hobby_id), ('Gesellschaftsspiele', hobby_id), ('Jagen', hobby_id), ('Joggen', hobby_id), ('Kampfsport', hobby_id), ('Klettern', hobby_id), ('Kochen', hobby_id), ('Lesen', hobby_id), ('Malen', hobby_id), ('Musik', hobby_id), ('Puzzlen', hobby_id), ('Radsport', hobby_id), ('Tanzen', hobby_id), ('Theater', hobby_id), ('Wandern', hobby_id), ('Yoga', hobby_id), ('Sonstiges', hobby_id);

WITH
    proje_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Projekt' LIMIT 1),
    alter_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Alter' LIMIT 1),
    gesch_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Geschlechtliche Identität' LIMIT 1),
    ethni_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Ethnische Herkunft' LIMIT 1),
    relig_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Religion' LIMIT 1),
    mutte_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Muttersprache' LIMIT 1),
    hobby_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Hobby' LIMIT 1),
    bildu_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Bildungsweg' LIMIT 1),
    beruf_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Berufserfahrung' LIMIT 1),
    ernae_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Ernährung' LIMIT 1),
    sozia_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Soziale Herkunft' LIMIT 1),
    discr_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Diskriminierung aufgrund sozialer Herkunft' LIMIT 1),
    sexue_id AS (SELECT id FROM dimension_category WHERE description LIKE 'Sexuelle Orientierung' LIMIT 1)
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
