CREATE TABLE IF NOT EXISTS `transaction` (

    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `description` VARCHAR(50),
    `transaction_date` DATE,
    `purchased_amount` DECIMAL(15)

)