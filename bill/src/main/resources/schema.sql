CREATE TABLE IF NOT EXISTS `bill` (
  `bill_id` int AUTO_INCREMENT PRIMARY KEY,
  `order_number` int NOT NULL,
  `order_amount` decimal NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `payment_number` int NOT NULL,
  `payment_amount` decimal NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL
);