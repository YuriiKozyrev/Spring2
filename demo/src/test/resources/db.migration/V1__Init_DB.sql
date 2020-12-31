--Buckets
create sequence bucket_seq start 1 increment 1;
create table buckets (
    id int8 not null,
    user_id int8, primary key (id)
);

--Users
create sequence user_seq start 1 increment 1;
create table users (
    id int8 not null,
    archive boolean not null,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255),
    bucket_id int8,
    primary key (id)
);

--Products
create sequence product_seq start 1 increment 1;
create table products (
    id int8 not null,
    price float8,
    title varchar(255),
    primary key (id)
);

--Categories
create sequence category_seq start 1 increment 1;
create table categories (
    id int8 not null,
    title varchar(255),
    primary key (id)
);

--Orders
create sequence order_seq start 1 increment 1;
create table orders (
    id int8 not null,
    address varchar(255),
    changed timestamp,
    created timestamp,
    status varchar(255),
    sum numeric(19, 2),
    user_id int8, primary key (id)
);

--Orders details
create sequence order_details_seq start 1 increment 1;
create table orders_details (
    id int8 not null,
    amount numeric(19, 2),
    price numeric(19, 2),
    order_id int8,
    product_id int8,
    primary key (id)
);

--Products in Bucket
create table bucket_products (
    bucket_id int8 not null,
    product_id int8 not null
);

--Products and Categories
create table products_categories (
    product_id int8 not null,
    category_id int8 not null
);


----Foreign keys and Links
--Link between Buckets and Users

alter table if exists buckets
    add constraint buckets_fk_user
    foreign key (user_id) references users;

--Link between Orders and User
 alter table if exists orders
    add constraint orders_fk_user
    foreign key (user_id) references users;

--Link between Buckets and Products throws bucket_products table
alter table if exists bucket_products
    add constraint bucket_products_fk_product
    foreign key (product_id) references products;

alter table if exists bucket_products
    add constraint bucket_products_fk_bucket
    foreign key (bucket_id) references buckets;

--Link between Orders and Products throws order_details table
alter table if exists orders_details
    add constraint orders_details_fk_order
    foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_product
    foreign key (product_id) references products;

--Link between Categories and Products throws product_categories table
alter table if exists products_categories
    add constraint products_categories_fk_category
    foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_product
    foreign key (product_id) references products;
