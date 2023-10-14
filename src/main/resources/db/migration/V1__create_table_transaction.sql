CREATE TABLE IF NOT EXISTS "transaction"."transaction" (

    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `description` VARCHAR(50),
    `transaction_date` DATE,
    `purchased_amount` DECIMAL(15)

)