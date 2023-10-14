CREATE TABLE transaction (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    purchased_amount DECIMAL(15);
)