INSERT INTO profile_entity(id, birth_year, email, name, was_changed_by_admin) VALUES
    (1, 1998, 'first.profile@some.tdl', 'first', false),
    (2, 1999, 'second.profile@some.tdl', 'second', false),
    (3, 1990, 'third.profile@some.tdl', 'third', false),
    (4, 1974, 'fourth.profile@some.tdl', 'fourth', false),
    (5, 1998, 'copyOfFirst.profile@some.tdl', 'copyOfFirst', false);

WITH algeria AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Algerien' LIMIT 1),
     andorra AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Andorra' LIMIT 1),
     meat AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Fleischesser' LIMIT 1),
     vegan AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Veganer' LIMIT 1),
     study AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Studium' LIMIT 1),
     training AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Ausbildung' LIMIT 1),
     male AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Männlich' LIMIT 1),
     female AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Weiblich' LIMIT 1),
     english AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Englisch' LIMIT 1),
     german AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Deutsch' LIMIT 1),
     externProject AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Externes Projekt' LIMIT 1),
     internProject  AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Externes Projekt' LIMIT 1),
     jewish AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Judentum' LIMIT 1),
     christian AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Christentum' LIMIT 1),
     heterosexual AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Heterosexuell' LIMIT 1),
     academicFamily AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Akademikerfamilie' LIMIT 1),
     yes AS (SELECT id FROM basic_dimension_selectable_option WHERE value='Ja' LIMIT 1),
     language AS (SELECT id FROM basic_dimension WHERE dimension_category_id=(SELECT id FROM dimension_category WHERE description='Muttersprache')),
     diet AS (SELECT id FROM basic_dimension WHERE dimension_category_id=(SELECT id FROM dimension_category WHERE description='Ernährung'))

INSERT INTO profile_entity_selected_basic_values (profile_entity_id, selected_basic_values_id, basic_dimension) VALUES
    (1, meat, diet), (1, algeria, language),
    (2, vegan, diet), (2, algeria, language),
    (3, vegan, diet), (3, algeria, language),
    (4, vegan, diet), (4, andorra, language),
    (5, meat, diet), (5, algeria, language);
-- TODO: complete Profiles
-- (1, (SELECT * FROM meat), (SELECT * FROM study), (SELECT * FROM male), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
-- (2, (SELECT * FROM vegan), (SELECT * FROM study), (SELECT * FROM male), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
-- (3, (SELECT * FROM vegan), (SELECT * FROM training), (SELECT * FROM female), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
-- (4, (SELECT * FROM vegan), (SELECT * FROM training), (SELECT * FROM female), (SELECT * FROM english), (SELECT * FROM andorra), (SELECT * FROM externProject), (SELECT * FROM christian),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) ),
-- (5, (SELECT * FROM meat), (SELECT * FROM study), (SELECT * FROM male), (SELECT * FROM german), (SELECT * FROM algeria), (SELECT * FROM internProject), (SELECT * FROM jewish),(SELECT * FROM lowExperience),(SELECT * FROM heterosexual), (SELECT * FROM academicFamily), (SELECT * FROM yes) );

WITH lowExperience AS (SELECT id FROM weighted_dimension_selectable_option WHERE value='0-3 Jahre' LIMIT 1),
     workxp AS (SELECT id FROM weighted_dimension WHERE dimension_category_id=(SELECT id FROM dimension_category WHERE description='Berufserfahrung'))
INSERT INTO profile_entity_selected_weighted_values (profile_entity_id, selected_weighted_values_id, weighted_dimension) VALUES
    (1, lowExperience, workxp),
    (2, lowExperience, workxp),
    (3, lowExperience, workxp),
    (4, lowExperience, workxp),
    (5, lowExperience, workxp);

WITH hobby AS (SELECT id FROM dimension_category WHERE description='Hobby')
INSERT INTO profile_entity_selected_multiselect_value (profile_id, multiselect_dimension) VALUES
    (1, hobby),
    (2, hobby),
    (3, hobby),
    (4, hobby),
    (5, hobby);

WITH profile1_selected AS (SELECT id FROM profile_entity_selected_multiselect_value WHERE profile_id=1),
     profile2_selected AS (SELECT id FROM profile_entity_selected_multiselect_value WHERE profile_id=2),
     profile3_selected AS (SELECT id FROM profile_entity_selected_multiselect_value WHERE profile_id=3),
     profile4_selected AS (SELECT id FROM profile_entity_selected_multiselect_value WHERE profile_id=4),
     profile5_selected AS (SELECT id FROM profile_entity_selected_multiselect_value WHERE profile_id=5),
     cooking AS (SELECT id FROM multiselect_dimension_selectable_option WHERE value='Kochen' LIMIT 1),
     gaming AS (SELECT id FROM multiselect_dimension_selectable_option WHERE value='Gaming' LIMIT 1),
     hunting AS (SELECT id FROM multiselect_dimension_selectable_option WHERE value='Jagen' LIMIT 1)
INSERT INTO profile_entity_selected_multiselect_value_selected_options (profile_entity_selected_multiselect_value_id, selected_options_id) VALUES
    (profile1_selected, cooking),
    (profile2_selected, cooking),
    (profile3_selected, cooking),
    (profile3_selected, hunting),
    (profile3_selected, gaming),
    (profile4_selected, gaming),
    (profile4_selected, hunting),
    (profile5_selected, cooking);



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
