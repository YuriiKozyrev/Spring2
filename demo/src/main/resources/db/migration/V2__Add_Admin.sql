INSERT INTO users (id, archive, email, name, password, role, bucket_id)
values (1, false, 'admin@myshop.ru', 'admin', '$2y$12$KmrlJTATnstCoWeMTqMnZOyrk1s82NuXwQJTFZ2d6tHZaU9K9AFfW', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;