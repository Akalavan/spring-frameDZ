DROP TABLE IF EXISTS rent_car;
DROP TABLE IF EXISTS rent;

CREATE TABLE rent_car (
    id integer NOT NULL,
    name character varying(1000),
    CONSTRAINT product_pley PRIMARY KEY (id)
);

CREATE TABLE rent (
    id integer NOT NULL,
    price bigint NOT NULL,
    date_from date NOT NULL,
    date_to date,
    rent_car integer NOT NULL,
    CONSTRAINT sales_period_pley PRIMARY KEY(id)
);