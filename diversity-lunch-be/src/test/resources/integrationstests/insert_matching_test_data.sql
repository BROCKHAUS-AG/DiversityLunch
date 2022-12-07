WITH algeria AS (SELECT id FROM  country_entity WHERE country_entity.descriptor='Algerien' LIMIT 1),
    andorra AS (SELECT id FROM  country_entity WHERE country_entity.descriptor='Andorra' LIMIT 1),
    meat AS (SELECT id FROM  diet_entity WHERE diet_entity.descriptor='Fleischesser' LIMIT 1),
    vegan AS (SELECT id FROM  diet_entity WHERE diet_entity.descriptor='Veganer' LIMIT 1),
    study AS (SELECT id FROM  education_entity WHERE education_entity.descriptor='Studium' LIMIT 1),
    training AS (SELECT id FROM  education_entity WHERE education_entity.descriptor='Ausbildung' LIMIT 1),
    male AS (SELECT id FROM  gender_entity WHERE gender_entity.descriptor='Männlich' LIMIT 1),
    female AS (SELECT id FROM  gender_entity WHERE gender_entity.descriptor='Weiblich' LIMIT 1),
    english AS (SELECT id FROM  language_entity WHERE language_entity.descriptor='Englisch' LIMIT 1),
    german AS (SELECT id FROM  language_entity WHERE language_entity.descriptor='Deutsch' LIMIT 1),
    externProject AS (SELECT id FROM project_entity WHERE project_entity.descriptor='Externes Projekt' LIMIT 1),
    internProject  AS (SELECT id FROM project_entity WHERE project_entity.descriptor='Externes Projekt' LIMIT 1),
    jewish AS (SELECT id FROM  religion_entity WHERE religion_entity.descriptor='Judentum' LIMIT 1),
    christian AS (SELECT id FROM  religion_entity WHERE religion_entity.descriptor='Christentum' LIMIT 1),
    lowExperience AS (SELECT id FROM  work_experience_entity WHERE work_experience_entity.descriptor='0-3 Jahre' LIMIT 1),
    heterosexual AS (SELECT id FROM sexual_orientation_entity WHERE sexual_orientation_entity.descriptor='Heterosexuell' LIMIT 1),
    academicFamily AS (SELECT id FROM social_background_entity WHERE social_background_entity.descriptor='Akademikerfamilie' LIMIT 1),
    yes AS (SELECT id FROM social_background_discrimination_entity WHERE social_background_discrimination_entity.descriptor='Ja' LIMIT 1)
INSERT INTO profile_entity(id, birth_year, email, name, diet_id, education_id, gender_id,mother_tongue_id, origin_country_id, project_id, religion_id, work_experience_id, sexual_orientation_id, social_background_id, social_background_discrimination_id ) VALUES
    (1, 1998, 'first.profile@some.tdl', 'first', (SELECT * FROM meat), (SELECT * FROM study), (SELECT * FROM male), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
    (2, 1999, 'second.profile@some.tdl', 'second', (SELECT * FROM vegan), (SELECT * FROM study), (SELECT * FROM male), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
    (3, 1990, 'third.profile@some.tdl', 'third', (SELECT * FROM vegan), (SELECT * FROM training), (SELECT * FROM female), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
    (4, 1974, 'fourth.profile@some.tdl', 'fourth', (SELECT * FROM vegan), (SELECT * FROM training), (SELECT * FROM female), (SELECT * FROM english), (SELECT * FROM andorra), (SELECT * FROM externProject), (SELECT * FROM christian),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
    (5, 1998, 'copyOfFirst.profile@some.tdl', 'copyOfFirst', (SELECT * FROM meat), (SELECT * FROM study), (SELECT * FROM male), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) );

WITH cooking AS (SELECT id FROM hobby_entity WHERE hobby_entity.descriptor='Kochen' LIMIT 1),
    gaming AS (SELECT id FROM hobby_entity WHERE hobby_entity.descriptor='Gaming' LIMIT 1),
    hunting AS (SELECT id FROM hobby_entity WHERE hobby_entity.descriptor='Jagen' LIMIT 1)

INSERT INTO profile_hobby(hobby_id, profile_id) VALUES
    ((SELECT * FROM cooking), 1),
    ((SELECT * FROM cooking), 2),
    ((SELECT * FROM cooking), 3),
    ((SELECT * FROM hunting), 3),
    ((SELECT * FROM gaming), 3),
    ((SELECT * FROM gaming), 4),
    ((SELECT * FROM hunting), 4),
    ((SELECT * FROM gaming), 5);



/*Score under 9, matching algorithm at date 26.09.2022 gives a score of 4*/
INSERT INTO meeting_proposal_entity VALUES (1, '2022-03-16 16:23:53.786276', false, '2022-03-18 12:30:00.000000', 1),
            (2, '2022-03-16 16:23:53.786276', false, '2022-03-18 12:30:00.000000', 2);

/*Score between 9 and 21, matching algorithm at date 26.09.2022 gives a score of 11*/
INSERT INTO meeting_proposal_entity VALUES (3, '2022-03-16 16:23:53.786276', false, '2022-03-18 11:30:00.000000', 1),
         (4, '2022-03-16 16:23:53.786276', false, '2022-03-18 11:30:00.000000', 3);

/*Score over 21, matching algorithm at date 26.09.2022 gives a score of 25*/
INSERT INTO meeting_proposal_entity VALUES (5, '2022-03-16 16:23:53.786276', false, '2022-04-05 13:30:00.000000', 1),
    (6, '2022-03-16 16:23:53.786276', false, '2022-04-05 13:30:00.000000', 4);

/*unmatched*/
INSERT INTO meeting_proposal_entity VALUES (7, '2022-03-16 16:23:53.786276', false, '2022-04-05 13:30:00.000000', 5);
