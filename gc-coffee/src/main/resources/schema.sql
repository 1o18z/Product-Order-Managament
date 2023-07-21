DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_items CASCADE;

CREATE TABLE products
(
    product_id   VARCHAR(50) PRIMARY KEY,
    product_name VARCHAR(20) NOT NULL,
    category     VARCHAR(50) NOT NULL,
    price        INT         NOT NULL
);

CREATE TABLE order_items
(
    order_item_id VARCHAR(50) PRIMARY KEY,
    order_id      VARCHAR(50) NOT NULL,
    product_id    VARCHAR(50) NOT NULL,
    quantity      INT         NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (product_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE orders
(
    order_id     VARCHAR(50) PRIMARY KEY,
    email        VARCHAR(50)  NOT NULL,
    address      VARCHAR(200) NOT NULL,
    order_status VARCHAR(50)  NOT NULL
);
