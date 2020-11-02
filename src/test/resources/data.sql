insert into rent_car (id, name) values (1, 'rapid_test');
insert into rent_car (id, name) values (2, 'polo_test');

insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (1, 1000, '2020-05-01', '2020-05-04', 1);
insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (2, 800, '2020-05-06', '2020-05-08', 1);
insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (3, 1500, '2020-05-01', '2020-05-07', 2);
insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (4, 2500, '2020-05-08', null, 2);
insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (5, 1200, '2020-05-09', '2020-05-13', 1);
insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (6, 1300, '2020-05-09', '2020-05-14', 2);
insert into rent (id, price, date_from, date_to, rent_car)
    VALUES (7, 1600, '2020-05-15', '2020-05-22', 1);