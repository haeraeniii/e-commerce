INSERT INTO customer(customer_name, balance) values ('정혜련', 5000000000);
INSERT INTO customer(customer_name, balance) values ('정필진', 5000000000);
INSERT INTO customer(customer_name, balance) values ('정재근', 3000000000);
INSERT INTO customer(customer_name, balance) values ('김종애', 3000000000);

INSERT INTO product (title) values ('블라우스');
INSERT INTO productOption (product_id, color, size, price, stock) values (1, 'pink', 'S', 30000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (1, 'pink', 'M', 30000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (1, 'pink', 'L', 30000, 10000);

INSERT INTO product (title) values ('청바지');
INSERT INTO productOption (product_id, color, size, price, stock) values (2, 'black', 'S', 20000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (2, 'black', 'M', 20000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (2, 'black', 'L', 20000, 10000);

INSERT INTO product (title) values ('부츠');
INSERT INTO productOption (product_id, color, size, price, stock) values (3, 'gray', 'S', 40000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (3, 'gray', 'M', 40000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (3, 'gray', 'L', 40000, 10000);

INSERT INTO product (title) values ('스커트');
INSERT INTO productOption (product_id, color, size, price, stock) values (4, 'yellow', 'S', 100, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (4, 'yellow', 'M', 100, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (4, 'yellow', 'L', 100, 10000);

INSERT INTO product (title) values ('코트');
INSERT INTO productOption (product_id, color, size, price, stock) values (5, 'green', 'S', 1000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (5, 'green', 'M', 1000, 10000);
INSERT INTO productOption (product_id, color, size, price, stock) values (5, 'green', 'L', 1000, 10000);

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-05 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (1, 1, 2, '블라우스', 'pink', 'M', 30000, 1, '2024-08-05 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (1, 2, 4, '청바지', 'black', 'S', 20000, 1, '2024-08-05 12:10:17');

INSERT INTO `order` (customer_id, created_at) values (2, '2024-08-06 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (2, 1, 2, '블라우스', 'pink', 'M', 30000, 1, '2024-08-05 12:10:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (2, 2, 4, '청바지', 'black', 'S', 20000, 1, '2024-08-05 12:10:17');

INSERT INTO `order` (customer_id, created_at) values (4, '2024-08-07 12:11:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (3, 3, 7, '부츠', 'pink', 'M', 40000, 1, '2024-08-07 12:11:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (3, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:11:17');

INSERT INTO `order` (customer_id, created_at) values (3, '2024-08-07 12:12:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (4, 3, 7, '부츠', 'pink', 'M', 40000, 1, '2024-08-07 12:12:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (4, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:12:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-08 12:11:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (5, 5, 14, '코트', 'pink', 'M', 1000, 1, '2024-08-08 12:11:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:13:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (6, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:13:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:14:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (7, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:14:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:15:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (8, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:15:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:16:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (9, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:16:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:17:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (10, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:17:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:18:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (11, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:18:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:19:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (12, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:19:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:20:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (13, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:20:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:21:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (14, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:21:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:22:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (15, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:22:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:23:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (16, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:23:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:24:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (17, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:24:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:25:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (18, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:25:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:26:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (19, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:26:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:27:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (20, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:27:17');

INSERT INTO `order` (customer_id, created_at) values (1, '2024-08-07 12:28:17');
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) values (21, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:28:17');

-- order 테이블에 데이터 삽입
INSERT INTO `order` (customer_id, created_at) VALUES
                                                  (1, '2024-08-06 14:28:17'),
                                                  (1, '2024-08-06 15:18:42'),
                                                  (1, '2024-08-07 09:55:01'),
                                                  (1, '2024-08-07 11:47:35'),
                                                  (1, '2024-08-06 20:33:58'),
                                                  (1, '2024-08-07 08:10:27'),
                                                  (1, '2024-08-06 17:29:04'),
                                                  (1, '2024-08-07 10:20:50'),
                                                  (1, '2024-08-07 13:05:11'),
                                                  (1, '2024-08-06 18:40:36'),
                                                  (1, '2024-08-06 19:15:29'),
                                                  (1, '2024-08-07 12:28:17'),
                                                  (1, '2024-08-07 14:21:05'),
                                                  (1, '2024-08-06 21:45:03'),
                                                  (1, '2024-08-06 22:50:59'),
                                                  (1, '2024-08-07 15:34:27'),
                                                  (1, '2024-08-06 23:59:01'),
                                                  (1, '2024-08-07 07:03:18'),
                                                  (1, '2024-08-06 16:05:44'),
                                                  (1, '2024-08-06 10:39:55'),
                                                  (1, '2024-08-06 12:47:10'),
                                                  (1, '2024-08-07 16:45:29'),
                                                  (1, '2024-08-07 18:55:33'),
                                                  (1, '2024-08-06 11:15:22'),
                                                  (1, '2024-08-07 19:30:45'),
                                                  (1, '2024-08-07 21:22:11'),
                                                  (1, '2024-08-06 13:55:04'),
                                                  (1, '2024-08-06 09:10:30'),
                                                  (1, '2024-08-07 22:40:27'),
                                                  (1, '2024-08-06 08:25:16'),
                                                  (1, '2024-08-06 07:17:49'),
                                                  (1, '2024-08-07 23:59:59'),
                                                  (1, '2024-08-06 06:03:51'),
                                                  (1, '2024-08-07 01:50:18'),
                                                  (1, '2024-08-06 04:29:03'),
                                                  (1, '2024-08-06 05:15:55'),
                                                  (1, '2024-08-07 02:40:36'),
                                                  (1, '2024-08-07 03:33:44'),
                                                  (1, '2024-08-06 02:21:11'),
                                                  (1, '2024-08-07 04:17:37'),
                                                  (1, '2024-08-07 05:10:22'),
                                                  (1, '2024-08-06 03:05:19'),
                                                  (1, '2024-08-07 06:08:59'),
                                                  (1, '2024-08-07 00:00:00'),
                                                  (1, '2024-08-06 00:00:00'),
                                                  (1, '2024-08-05 14:28:17'),
                                                  (1, '2024-08-05 15:18:42'),
                                                  (1, '2024-08-05 09:55:01'),
                                                  (1, '2024-08-05 11:47:35'),
                                                  (1, '2024-08-05 20:33:58'),
                                                  (1, '2024-08-05 08:10:27'),
                                                  (1, '2024-08-05 17:29:04'),
                                                  (1, '2024-08-05 10:20:50'),
                                                  (1, '2024-08-05 13:05:11'),
                                                  (1, '2024-08-05 18:40:36'),
                                                  (1, '2024-08-05 19:15:29'),
                                                  (1, '2024-08-05 12:28:17'),
                                                  (1, '2024-08-05 14:21:05'),
                                                  (1, '2024-08-05 21:45:03'),
                                                  (1, '2024-08-05 22:50:59'),
                                                  (1, '2024-08-05 15:34:27'),
                                                  (1, '2024-08-05 23:59:01'),
                                                  (1, '2024-08-05 07:03:18'),
                                                  (1, '2024-08-05 16:05:44'),
                                                  (1, '2024-08-05 10:39:55'),
                                                  (1, '2024-08-05 12:47:10'),
                                                  (1, '2024-08-05 16:45:29'),
                                                  (1, '2024-08-05 18:55:33'),
                                                  (1, '2024-08-05 11:15:22'),
                                                  (1, '2024-08-05 19:30:45'),
                                                  (1, '2024-08-05 21:22:11'),
                                                  (1, '2024-08-05 13:55:04'),
                                                  (1, '2024-08-05 09:10:30'),
                                                  (1, '2024-08-05 22:40:27'),
                                                  (1, '2024-08-05 08:25:16'),
                                                  (1, '2024-08-05 07:17:49'),
                                                  (1, '2024-08-05 23:59:59'),
                                                  (1, '2024-08-05 06:03:51'),
                                                  (1, '2024-08-05 01:50:18'),
                                                  (1, '2024-08-05 04:29:03'),
                                                  (1, '2024-08-05 05:15:55'),
                                                  (1, '2024-08-05 02:40:36'),
                                                  (1, '2024-08-05 03:33:44'),
                                                  (1, '2024-08-05 02:21:11'),
                                                  (1, '2024-08-05 04:17:37'),
                                                  (1, '2024-08-05 05:10:22'),
                                                  (1, '2024-08-05 03:05:19'),
                                                  (1, '2024-08-05 06:08:59'),
                                                  (1, '2024-08-05 00:00:00');

-- orderItem 테이블에 데이터 삽입
INSERT INTO orderItem (order_id, product_id, product_option_id, product_name, color, size, price, order_quantity, ordered_at) VALUES
                                                                                                                                  (22, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 14:28:17'),
                                                                                                                                  (23, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 15:18:42'),
                                                                                                                                  (24, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 09:55:01'),
                                                                                                                                  (25, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 11:47:35'),
                                                                                                                                  (26, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 20:33:58'),
                                                                                                                                  (27, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 08:10:27'),
                                                                                                                                  (28, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 17:29:04'),
                                                                                                                                  (29, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 10:20:50'),
                                                                                                                                  (30, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 13:05:11'),
                                                                                                                                  (31, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 18:40:36'),
                                                                                                                                  (32, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 19:15:29'),
                                                                                                                                  (33, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 12:28:17'),
                                                                                                                                  (34, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 14:21:05'),
                                                                                                                                  (35, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 21:45:03'),
                                                                                                                                  (36, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 22:50:59'),
                                                                                                                                  (37, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 15:34:27'),
                                                                                                                                  (38, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 23:59:01'),
                                                                                                                                  (39, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 07:03:18'),
                                                                                                                                  (40, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 16:05:44'),
                                                                                                                                  (41, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 10:39:55'),
                                                                                                                                  (42, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 12:47:10'),
                                                                                                                                  (43, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 16:45:29'),
                                                                                                                                  (44, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 18:55:33'),
                                                                                                                                  (45, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 11:15:22'),
                                                                                                                                  (46, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 19:30:45'),
                                                                                                                                  (47, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 21:22:11'),
                                                                                                                                  (48, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 13:55:04'),
                                                                                                                                  (49, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 09:10:30'),
                                                                                                                                  (50, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 22:40:27'),
                                                                                                                                  (51, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 08:25:16'),
                                                                                                                                  (52, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 07:17:49'),
                                                                                                                                  (53, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 23:59:59'),
                                                                                                                                  (54, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 06:03:51'),
                                                                                                                                  (55, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 01:50:18'),
                                                                                                                                  (56, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 04:29:03'),
                                                                                                                                  (57, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 05:15:55'),
                                                                                                                                  (58, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 02:40:36'),
                                                                                                                                  (59, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 03:33:44'),
                                                                                                                                  (60, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 02:21:11'),
                                                                                                                                  (61, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 04:17:37'),
                                                                                                                                  (62, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 05:10:22'),
                                                                                                                                  (63, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 03:05:19'),
                                                                                                                                  (64, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 06:08:59'),
                                                                                                                                  (65, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-07 00:00:00'),
                                                                                                                                  (66, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-06 00:00:00'),
                                                                                                                                  (67, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 14:28:17'),
                                                                                                                                  (68, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 15:18:42'),
                                                                                                                                  (69, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 09:55:01'),
                                                                                                                                  (70, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 11:47:35'),
                                                                                                                                  (71, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 20:33:58'),
                                                                                                                                  (72, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 08:10:27'),
                                                                                                                                  (73, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 17:29:04'),
                                                                                                                                  (74, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 10:20:50'),
                                                                                                                                  (75, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 13:05:11'),
                                                                                                                                  (76, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 18:40:36'),
                                                                                                                                  (77, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 19:15:29'),
                                                                                                                                  (78, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 12:28:17'),
                                                                                                                                  (79, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 14:21:05'),
                                                                                                                                  (80, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 21:45:03'),
                                                                                                                                  (81, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 22:50:59'),
                                                                                                                                  (82, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 15:34:27'),
                                                                                                                                  (83, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 23:59:01'),
                                                                                                                                  (84, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 07:03:18'),
                                                                                                                                  (85, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 16:05:44'),
                                                                                                                                  (86, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 10:39:55'),
                                                                                                                                  (87, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 12:47:10'),
                                                                                                                                  (88, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 16:45:29'),
                                                                                                                                  (89, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 18:55:33'),
                                                                                                                                  (90, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 11:15:22'),
                                                                                                                                  (91, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 19:30:45'),
                                                                                                                                  (92, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 21:22:11'),
                                                                                                                                  (93, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 13:55:04'),
                                                                                                                                  (94, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 09:10:30'),
                                                                                                                                  (95, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 22:40:27'),
                                                                                                                                  (96, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 08:25:16'),
                                                                                                                                  (97, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 07:17:49'),
                                                                                                                                  (98, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 23:59:59'),
                                                                                                                                  (99, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 06:03:51'),
                                                                                                                                  (100, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 01:50:18'),
                                                                                                                                  (101, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 04:29:03'),
                                                                                                                                  (102, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 05:15:55'),
                                                                                                                                  (103, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 02:40:36'),
                                                                                                                                  (104, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 03:33:44'),
                                                                                                                                  (105, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 02:21:11'),
                                                                                                                                  (106, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 04:17:37'),
                                                                                                                                  (107, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 05:10:22'),
                                                                                                                                  (108, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 03:05:19'),
                                                                                                                                  (109, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 06:08:59'),
                                                                                                                                  (110, 4, 10, '스커트', 'black', 'S', 100, 1, '2024-08-05 00:00:00');
-- CREATE INDEX idx_order_customer ON `order` (customer_id);
CREATE INDEX idx_order_created_at ON `order` (created_at);
CREATE INDEX idx_order_created_customer ON `order` (created_at, customer_id);
CREATE INDEX idx_orderItem_ordered_at_product_id ON orderItem (ordered_at, product_id);
-- CREATE INDEX idx_orderItem_ordered_at ON orderItem (ordered_at);