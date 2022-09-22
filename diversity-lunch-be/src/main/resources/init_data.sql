Insert Into country_entity(descriptor) values ('Algerien'),('Andorra'),('Angola'),('Antiguaundbarbuda'),('Argentinien'),('Armenien'),('Aserbaidschan'),('Australien'),('Bahamas'),('Bahrain'),('Bangladesch'),('Barbados'),('Belgien'),('Belize'),('Benin'),('Bhutan'),('Bolivien'),('Botswana'),('Botwana'),('Brasilien'),('Brunei'),('Bulgarien'),('Burkinafaso'),('Burundi'),('Chile'),('Chinarepubliktaiwan'),('Costarica'),('Deutschland'),('Dominica'),('Dominikanischerepublik'),('Dschibuti'),('Danemark'),('Ecuador'),('Elsalvador'),('Elfenbeinkuste'),('Eritrea'),('Estland'),('Eswatini'),('Finnland'),('Frankreich'),('Faroer'),('Gabun'),('Gambia'),('Georgien'),('Ghana'),('Ginea'),('Gineabissau'),('Grenada'),('Griechenland'),('Gronland'),('Guatemala'),('Guyana'),('Haiti'),('Honduras'),('Hongkong'),('Indien'),('Indonesien'),('Irak'),('Iran'),('Irland'),('Island'),('Israel'),('Italien'),('Jamaika'),('Japan'),('Jemen'),('Jordanien'),('Kambodscha'),('Kamerun'),('Kanada'),('Kapverde'),('Katar'),('Kenia'),('Kirgisistan'),('Kiribati'),('Kolumbien'),('Komoren'),('Kongo'),('Kosovo'),('Kroatien'),('Kuba'),('Kuwait'),('Laos'),('Lesotho'),('Lettland'),('Libanon'),('Liberia'),('Libyen'),('Liechtenstein'),('Litauen'),('Luxemburg'),('Macau'),('Madagaskar'),('Malawi'),('Malaysia'),('Malediven'),('Mali'),('Malta'),('Marokko'),('Mauretanien'),('Mauritius'),('Mexiko'),('Moldawien'),('Monaco'),('Mongolei'),('Mosambik'),('Motenegro'),('Myanmar'),('Namibia'),('Nepal'),('Neuseeland'),('Nicaragua'),('Niederlande'),('Niger'),('Nigeria'),('Nordkorea'),('Nordmazedonien'),('Norwegen'),('Oman'),('Pakistan'),('Palastina'),('Panama'),('Papuaneuguinea'),('Paraguay'),('Peru'),('Philippinen'),('Polen'),('Portugal'),('Republikmoldau'),('Ruanda'),('Rumanien'),('Russland'),('Sambia'),('Saudiarabien'),('Schweden'),('Schweiz'),('Senegal'),('Serbien'),('Seychellen'),('Sierraleone'),('Simbabwe'),('Singapur'),('Slowakei'),('Slowenien'),('Somalia'),('Spanien'),('Srilanka'),('Stkittsundnevis'),('Stlucia'),('Stvincentunddiegrenadinen'),('Sudan'),('Suriname'),('Syrien'),('Saotomeundprincipe'),('Sudafrika'),('Sudkorea'),('Sudsudan'),('Tadschikistan'),('Taiwan'),('Tansania'),('Thailand'),('Timorleste'),('Togo'),('Tonga'),('Trinidadundtobago'),('Tschad'),('Tschechien'),('Tunesien'),('Turkmenistan'),('Tuvalu'),('Turkei'),('Usa'),('Uganda'),('Ukraine'),('Ungarn'),('Uruguay'),('Usbekistan'),('Vatikanstadt'),('Venezuela'),('Vereinigtearabischeemirate'),('Vereinigteskonigreich'),('Vietnam'),('Volksrepublikchina'),('Wallisundfutuna'),('Weissrussland'),('Westsahara'),('Zentralafrikanischerepublik'),('Zypern'),('Agypten'),('Aquatorialguinea'),('Athiopien'),('Osterreich'),('Albanien'),('Bosnienundherzegowina'),('Kasachstan'),('Sanmarino');
Insert Into diet_entity(descriptor)  values ('Fleischesser'),('Vegetarier'),('Veganer'), ('Sonstige');
Insert Into education_entity(descriptor) values ('Ausbildung'), ('Studium'), ('Andere Wege');
Insert Into gender_entity(descriptor) values ('Männlich'), ('Weiblich'), ('Keine Angabe');
Insert Into hobby_category_entity(descriptor) values ('Sport'), ('Kreatives'), ('Natur'), ('Unterhaltung'), ('Kultur'), ('Sonstiges');
Insert Into language_entity(descriptor) values ('Akan'),('Amharisch'),('Arabisch'),('Aserbaidschanisch'),('Assamesisch'),('Awadhi'),('Belutschisch'),('Bengalisch'),('Bhojpuri'),('Bulgarisch'),('Burmesisch'),('Cebuano'),('Chhattisgarhi'),('Chichewa'),('Chittagonisch'),('Dakhini'),('Deutsch'),('Dhundari'),('Daenisch'),('Englisch'),('Estnisch'),('Finnisch'),('Franzoesisch'),('Fulfulde'),('Ganchinesisch'),('Griechisch'),('Guarani'),('Gujarati'),('Haitanischeskreol'),('Hakkachinesisch'),('Haryanvi'),('Hausa'),('Hiligaynon'),('Hindi'),('Hmong'),('Hocharabisch'),('Igbo'),('Ilokano'),('Indonesisch'),('Irisch'),('Italienisch'),('Japanisch'),('Javanisch'),('Jinchinesisch'),('Kannada'),('Kantonesisch'),('Kasachisch'),('Katalanisch'),('Khmer'),('Kinyarwanda'),('Kirundi'),('Konkani'),('Koreanisch'),('Kroatisch'),('Kurdisch'),('Lettisch'),('Litauisch'),('Maduresisch'),('Magahi'),('Maithili'),('Malagasy'),('Malaiisch'),('Malayalam'),('Maltesisch'),('Marathi'),('Marwari'),('Minanchinesisch'),('Minbeichinesisch'),('Mindongchinesisch'),('Moore'),('Nahuatl'),('Nepali'),('Niederlaendisch'),('Oriya'),('Oromo'),('Panjabi'),('Paschtunisch'),('Persisch'),('Polnisch'),('Portugiesisch'),('Quechua'),('Rumaenisch'),('Russisch'),('Saraiki'),('Schwedisch'),('Sindhi'),('Singhalesisch'),('Slowakisch'),('Slowenisch'),('Somali'),('Spanisch'),('Suaheli'),('Sundanesisch'),('Sylheti'),('Tagalog'),('Tamil'),('Telugu'),('Thai'),('Tschechisch'),('Turkmenisch'),('Tuerkisch'),('Uigurisch'),('Ukrainisch'),('Ungarisch'),('Urdu'),('Usbekisch'),('Vietnamesisch'),('Weissrussisch'),('Wuchinesisch'),('Xiangchinesisch'),('Yoruba'),('Zhuangchinesisch'),('Isixhosa'),('Isizulu'),('Aegyptischarabisch'),('Mandarinchinesisch');
Insert Into project_entity(descriptor) values ('Externes Projekt'), ('Internes Projekt');
Insert Into religion_entity(descriptor) values ('Judentum'),('Christentum'),('Islam'),('Hinduismus'),('Buddhismus'),('Sonstige'),('Kein Glaube');
Insert Into work_experience_entity(descriptor) values ('0-3 Jahre'), ('4-10 Jahre'),('über 10 Jahre');

WITH sport AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Sport' LIMIT 1),
kreatives AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Kreatives' LIMIT 1),
natur AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Natur' LIMIT 1),
unterhaltung AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Unterhaltung' LIMIT 1),
kultur AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Kultur' LIMIT 1),
sonstiges AS (SELECT id FROM hobby_category_entity WHERE hobby_category_entity.descriptor = 'Sonstiges' LIMIT 1)

INSERT INTO hobby_entity(descriptor, category_id) values ('Angeln', (SELECT * FROM natur)), ('Ballsport', (SELECT * FROM sport)), ('Bogenschießen', (SELECT * FROM sport)), ('Camping', (SELECT * FROM natur)),
                                                         ('Dart', (SELECT * FROM sport)), ('DIY', (SELECT * FROM kreatives)), ('Filme', (SELECT * FROM unterhaltung)), ('Gaming', (SELECT * FROM unterhaltung)),
                                                         ('Gärtnern', (SELECT * FROM natur)), ('Fotografie', (SELECT * FROM kreatives)), ('Gesellschaftsspiele', (SELECT * FROM kultur)),
                                                         ('Jagen', (SELECT * FROM natur)), ('Joggen', (SELECT * FROM sport)), ('Kampfsport', (SELECT * FROM sport)), ('Klettern', (SELECT * FROM sport)),
                                                         ('Kochen', (SELECT * FROM kultur)), ('Lesen', (SELECT * FROM unterhaltung)), ('Malen', (SELECT * FROM kreatives)), ('Musik', (SELECT * FROM unterhaltung)),
                                                         ('Puzzlen', (SELECT * FROM unterhaltung)), ('Radsport', (SELECT * FROM sport)), ('Tanzen', (SELECT * FROM sport)), ('Theater', (SELECT * FROM kultur)),
                                                         ('Wandern', (SELECT * FROM natur)), ('Yoga', (SELECT * FROM sport)), ('Sonstiges', (SELECT * FROM sonstiges));