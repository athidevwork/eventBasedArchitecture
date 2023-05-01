CREATE TABLE IF NOT EXISTS `order_test` (
  `order_id` int AUTO_INCREMENT PRIMARY KEY,
  `customer_name` varchar(100) NOT NULL,
  `total_amount` decimal NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL
);