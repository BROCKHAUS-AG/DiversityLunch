
/*score under 8*/
insert into profile_entity
values (1, 1998, 'ExampleCompany1', 'VEGAN', 'APPRENTICESHIP', 'asteg@brockhaus-ag.de', 'NOT_SPECIFIED',
        'DART', 'DEUTSCH', 'Alfons', 'ASERBAIDSCHAN', 'OTHER', 'HIGH_EXPERIENCE');
insert into profile_entity
values (2, 1990, 'ExampleCompany1', 'VEGAN', 'APPRENTICESHIP', 'uben@brockhaus-ag.de', 'NOT_SPECIFIED', 'DART',
        'DEUTSCH', 'Ursula', 'DEUTSCHLAND', 'OTHER', 'MID_EXPERIENCE');

/*score over 9*/
insert into profile_entity
values (3, 1998, 'ExampleCompany1', 'MEAT', 'APPRENTICESHIP', 'rkam@brockhaus-ag.de', 'MALE',
        'DART', 'DEUTSCH', 'Robert', 'ASERBAIDSCHAN', 'OTHER', 'HIGH_EXPERIENCE');
insert into profile_entity
values (4, 1990, 'ExampleCompany1', 'VEGAN', 'APPRENTICESHIP', 'osand@brockhaus-ag.de', 'NOT_SPECIFIED', 'DART',
        'DEUTSCH', 'Otto', 'DEUTSCHLAND', 'OTHER', 'LOW_EXPERIENCE');

/*score over 21*/
insert into profile_entity
values (5, 1974, 'ExampleCompany1', 'VEGAN', 'STUDY', 'bklam@brockhaus-ag.de', 'FEMALE', 'ART', 'DEUTSCH',
        'Berta', 'ASERBAIDSCHAN', 'NO_FAITH', 'LOW_EXPERIENCE');
insert into profile_entity
values (6, 1995, 'ExampleCompany2', 'OTHER', 'OTHER', 'dlohn@brockhaus-ag.de', 'NOT_SPECIFIED', 'JOGGING',
        'BELUTSCHISCH', 'Dieter', 'DEUTSCHLAND', 'OTHER', 'HIGH_EXPERIENCE');

/*unmatched*/
insert into profile_entity
values (7, 1995, 'ExampleCompany2', 'OTHER', 'OTHER', 'awrak@brockhaus-ag.de', 'NOT_SPECIFIED', 'JOGGING',
        'BELUTSCHISCH', 'Annie', 'DEUTSCHLAND', 'OTHER', 'HIGH_EXPERIENCE');

/*score under 8*/
insert into meeting_proposal_entity
values (1, '2022-03-16 16:23:53.786276', false, '2022-03-18 12:30:00.000000', 1);
insert into meeting_proposal_entity
values (2, '2022-03-16 16:23:53.786276', false, '2022-03-18 12:30:00.000000', 2);

/*score over 9*/
insert into meeting_proposal_entity
values (3, '2022-03-16 16:23:53.786276', false, '2022-03-18 11:30:00.000000', 3);
insert into meeting_proposal_entity
values (4, '2022-03-16 16:23:53.786276', false, '2022-03-18 11:30:00.000000', 4);

/*score over 21*/
insert into meeting_proposal_entity
values (5, '2022-03-16 16:23:53.786276', false, '2022-04-05 13:30:00.000000', 5);
insert into meeting_proposal_entity
values (6, '2022-03-16 16:23:53.786276', false, '2022-04-05 13:30:00.000000', 6);

/*unmatched*/
insert into meeting_proposal_entity
values (7, '2022-03-16 16:23:53.786276', false, '2022-04-05 13:30:00.000000', 7);

/*meeting*/
/*insert into meeting_entity
values (1, '2022-03-16 16:23:53.786276','2022-04-05 13:30:00.000000','RELIGION2', 30, 5, 6);
insert into meeting_entity
values (2, '2022-03-17 13:22:53.786276','2022-04-06 12:30:00.000000','DIET1', 10, 3, 6);*/
