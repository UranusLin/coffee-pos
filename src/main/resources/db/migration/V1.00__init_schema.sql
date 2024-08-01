CREATE TABLE `goods` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200),
  `amount` INT NOT NULL,
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `goods_price` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `goods_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `products` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200),
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `product_price` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `purchase_record` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `goods_id` INT NOT NULL,
  `goods_price_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `total_price` DECIMAL(10,2) NOT NULL,
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `stock_record` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `goods_id` INT NOT NULL,
  `purchase_record_id` INT,
  `orig_amount` INT NOT NULL,
  `changed_amount` INT NOT NULL,
  `reason` VARCHAR(100),
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `sales_record` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `product_price_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `total_price` DECIMAL(10,2) NOT NULL,
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `accounting_record` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `record_type` varchar(50) NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `description` VARCHAR(200),
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `discounts` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200),
  `discount_type` varchar(50) NOT NULL,
  `discount_value` DECIMAL(10,2) NOT NULL,
  `start_date` DATETIME,
  `end_date` DATETIME,
  `create_at` DATETIME DEFAULT (CURRENT_TIMESTAMP),
  `update_at` DATETIME DEFAULT (CURRENT_TIMESTAMP) COMMENT 'ON UPDATE CURRENT_TIMESTAMP'
);

CREATE TABLE `product_discounts` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `discount_id` INT NOT NULL
);

CREATE TABLE `sale_discounts` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `sales_record_id` INT NOT NULL,
  `discount_id` INT NOT NULL,
  `discount_value` DECIMAL(10,2) NOT NULL
);

CREATE TABLE `orders` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `total_amount` int NOT NULL,
  `total_price` int NOT NULL,
  `order_type` varchar(50)
);

ALTER TABLE `goods_price` ADD FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`);

ALTER TABLE `product_price` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `purchase_record` ADD FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`);

ALTER TABLE `purchase_record` ADD FOREIGN KEY (`goods_price_id`) REFERENCES `goods_price` (`id`);

ALTER TABLE `purchase_record` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `stock_record` ADD FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`);

ALTER TABLE `stock_record` ADD FOREIGN KEY (`purchase_record_id`) REFERENCES `purchase_record` (`id`);

ALTER TABLE `sales_record` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `sales_record` ADD FOREIGN KEY (`product_price_id`) REFERENCES `product_price` (`id`);

ALTER TABLE `sales_record` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `product_discounts` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `product_discounts` ADD FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`);

ALTER TABLE `sale_discounts` ADD FOREIGN KEY (`sales_record_id`) REFERENCES `sales_record` (`id`);

ALTER TABLE `sale_discounts` ADD FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`);
