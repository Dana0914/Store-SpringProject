CREATE TABLE categories (
    id SERIAL4 NOT NULL,
    name VARCHAR (20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE items (
    id SERIAL4 NOT NULL ,
    category_id int4 NOT NULL ,
    name VARCHAR (20) NOT NULL ,
    price int4 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

ALTER TABLE items
ADD COLUMN quantity INTEGER NOT NULL DEFAULT 0;

ALTER TABLE items
    DROP COLUMN quantity;


CREATE TABLE options (
    id       SERIAL4       NOT NULL,
    category_id int4       NOT NULL ,
    name     VARCHAR (20)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE values (
    id SERIAL4 NOT NULL,
    option_id int4 NOT NULL,
    item_id int4 NOT NULL,
    name VARCHAR (20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (option_id) REFERENCES options (id),
    FOREIGN KEY (item_id) REFERENCES items (id)
);

CREATE TABLE users (
    id SERIAL4 NOT NULL,
    role int2 NOT NULL,
    email VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    id SERIAL4 NOT NULL,
    users_id int4 NOT NULL,
    items_id int4 NOT NULL,
    status int2 NOT NULL,
    order_date DATE NOT NULL DEFAULT CURRENT_DATE,
    address VARCHAR(40) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(items_id) REFERENCES items (id)
);

CREATE TABLE orders_items (
    order_id INT4 NOT NULL,
    items_id INT4 NOT NULL,
    PRIMARY KEY (order_id, items_id),
    FOREIGN KEY(order_id) REFERENCES orders(id),
    FOREIGN KEY(items_id) REFERENCES items(id)
);

CREATE TABLE basket (
    id SERIAL4 NOT NULL,
    users_id int4 NOT NULL,
    items_id int4 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (items_id) REFERENCES items (id)

);

ALTER TABLE basket
    ADD COLUMN quantity INTEGER NOT NULL default 0;

ALTER TABLE basket
    DROP COLUMN quantity;

ALTER TABLE basket
    ADD COLUMN quantity INTEGER NOT NULL default 0;




CREATE TABLE review (
    id SERIAL4 NOT NULL,
    users_id int4 NOT NULL,
    items_id int4 NOT NULL,
    rating smallint NOT NULL CHECK(rating >= 1 AND rating <= 5),
    review TEXT NOT NULL,
    review_status VARCHAR(30) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (items_id) REFERENCES items (id),
    FOREIGN KEY (users_id) REFERENCES users (id)
);


INSERT INTO categories (name)
VALUES ('processors'),
       ('monitors');

INSERT INTO items (category_id, name, price)
VALUES (1, 'Intel Core I9 9900', 780000),
       (1,'AMD Ryzen R7 7700', 650000),
       (2,'Samsung SU556270', 450000),
       (2, 'AOC Z215S659', 535000);

INSERT INTO options (category_id, name)
VALUES (1, 'manufacturer' ),
       (1, 'amount_of_cores'),
       (1, 'socket'),
       (2, 'manufacturer'),
       (2, 'diagonal'),
       (2, 'matrix'),
       (2, 'resolution');

INSERT INTO values (option_id, item_id, name)
VALUES (1, 1,'Intel'),
       (2, 1,'8'),
       (3, 1,'1250'),
       (4, 2,'Samsung'),
       (5, 2,'27'),
       (6, 2,'TN'),
       (7,2,'2560*1440'),
       (1, 3,'AMD'),
       (2,3,'12'),
       (3, 3,'AM4'),
       (4,4,'AOC'),
       (5,4,'21.5'),
       (6,4,'AH-IPS'),
       (7,4,'1920*1080');

INSERT INTO users (role, email, password, first_name, last_name)
VALUES (1, 'henry08@gmail.com', 'sha-506affbd-kyldsv223', 'henry', 'hetz'),
       (2, 'saraS566@hotmail.com', 'klj6-gb5d-bng5b', 'sara', 'konnor');

INSERT INTO orders (users_id, items_id, status, order_date, address)
VALUES (1, 2, 1, '2024-7-18', 'New Valley 18 CA'),
       (2, 2, 1, '2024-6-30', 'Kennington highway 699');

INSERT INTO orders_items (order_id, items_id)
VALUES (1, 2),
       (2, 2);

INSERT INTO basket (users_id, items_id)
VALUES (1, 2),
       (2, 2);

INSERT INTO review (users_id, items_id, rating, review, review_status)
VALUES (1, 2, 4, 'very good', 'published'),
       (2, 2, 1, 'bad', 'published')


