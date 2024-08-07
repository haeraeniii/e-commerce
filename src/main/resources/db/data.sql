INSERT INTO customer(customer_name, balance) values ('정혜련', 100000);
INSERT INTO customer(customer_name, balance) values ('정필진', 100000);
INSERT INTO customer(customer_name, balance) values ('정재근', 100000);
INSERT INTO customer(customer_name, balance) values ('김종애', 100000);

INSERT INTO product (title) values ('블라우스');
INSERT INTO productOption (product_id, color, size, price, stock) values (1, 'pink', 'S', 30000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (1, 'pink', 'M', 30000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (1, 'pink', 'L', 30000, 10);

INSERT INTO product (title) values ('청바지');
INSERT INTO productOption (product_id, color, size, price, stock) values (2, 'black', 'S', 20000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (2, 'black', 'M', 20000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (2, 'black', 'L', 20000, 10);

INSERT INTO product (title) values ('부츠');
INSERT INTO productOption (product_id, color, size, price, stock) values (3, 'gray', 'S', 40000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (3, 'gray', 'M', 40000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (3, 'gray', 'L', 40000, 10);

INSERT INTO product (title) values ('스커트');
INSERT INTO productOption (product_id, color, size, price, stock) values (4, 'yellow', 'S', 10000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (4, 'yellow', 'M', 10000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (4, 'yellow', 'L', 10000, 10);

INSERT INTO product (title) values ('코트');
INSERT INTO productOption (product_id, color, size, price, stock) values (5, 'green', 'S', 100000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (5, 'green', 'M', 100000, 10);
INSERT INTO productOption (product_id, color, size, price, stock) values (5, 'green', 'L', 100000, 10);

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-05 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (1, 1, 2, '블라우스', 'pink', 'M', 30000, 1, '2024-08-05 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (1, 2, 4, '청바지', 'black', 'S', 20000, 1, '2024-08-05 12:10:17');

INSERT INTO `order` (customer_id, created_at) values (2, '2024-08-06 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (2, 1, 2, '블라우스', 'pink', 'M', 30000, 1, '2024-08-05 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (2, 2, 4, '청바지', 'black', 'S', 20000, 1, '2024-08-05 12:10:17');