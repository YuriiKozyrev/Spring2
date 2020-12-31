INSERT INTO users (id, archive, email, name, password, role)
values (1, false, 'admin@myshop.ru', 'admin', '$2y$12$KmrlJTATnstCoWeMTqMnZOyrk1s82NuXwQJTFZ2d6tHZaU9K9AFfW', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;