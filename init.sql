CREATE DATABASE if not exists test;

USE test;

CREATE table catalog_item(
    id BIGINT NOT NULL AUTO_INCREMENT,
    description varchar(255),
    name varchar(255),
    price decimal(19, 2),
    primary key (id)
);

CREATE table catalog_item_categories(
    catalog_item_id bigint not null,
    categories varchar(255)
);

CREATE table catalog_item_images(
    catalog_item_id bigint not null,
    images varchar(255)
);