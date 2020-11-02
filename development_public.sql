create table rent_car
(
    id   serial      not null
        constraint rent_car_pk
            primary key,
    name varchar(50) not null
);

alter table rent_car
    owner to postgres;

create unique index rent_car_id_uindex
    on rent_car (id);

INSERT INTO public.rent_car (id, name) VALUES (1, 'polo');
INSERT INTO public.rent_car (id, name) VALUES (2, 'rapid');
INSERT INTO public.rent_car (id, name) VALUES (3, 'kaptur');
INSERT INTO public.rent_car (id, name) VALUES (4, 'qashqai');



create table rent
(
    id        bigserial not null
        constraint rent_pk
            primary key,
    price     integer   not null,
    date_from date      not null,
    date_to   date,
    rent_car  integer   not null
        constraint rent_rent_car_id_fk
            references rent_car
);

alter table rent
    owner to postgres;

create unique index rent_id_uindex
    on rent (id);

INSERT INTO public.rent (id, price, date_from, date_to, rent_car) VALUES (1, 500, '2020-10-30', '2020-10-31', 1);
INSERT INTO public.rent (id, price, date_from, date_to, rent_car) VALUES (3, 2500, '2020-10-30', null, 2);
INSERT INTO public.rent (id, price, date_from, date_to, rent_car) VALUES (4, 1500, '2020-11-01', '2020-11-06', 3);
INSERT INTO public.rent (id, price, date_from, date_to, rent_car) VALUES (5, 1500, '2020-10-30', '2020-11-03', 3);
INSERT INTO public.rent (id, price, date_from, date_to, rent_car) VALUES (6, 3000, '2020-11-01', null, 4);
INSERT INTO public.rent (id, price, date_from, date_to, rent_car) VALUES (2, 5000, '2020-10-30', '2020-11-02', 2);