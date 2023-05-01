CREATE TABLE IF NOT EXISTS `event_store` (
  `event_store_id` int AUTO_INCREMENT  PRIMARY KEY,
  `context_id` varchar(50) NOT NULL,
  `event_name` varchar(100) NOT NULL,
  `payload` CHARACTER LARGE OBJECT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL
);