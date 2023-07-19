DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE products
(
    product_id   VARCHAR(50) PRIMARY KEY,
    product_name VARCHAR(20) NOT NULL,
    category     VARCHAR(50) NOT NULL,
    price        BIGINT      NOT NULL,
    description  VARCHAR(500) DEFAULT NULL,
    created_at   TIMESTAMP   NOT NULL
);

CREATE TABLE orders
(
    order_id     VARCHAR(50) PRIMARY KEY,
    email        VARCHAR(50)  NOT NULL,
    address      VARCHAR(200) NOT NULL,
    menu         VARCHAR(16)  NOT NULL,
    quantity     INT          NOT NULL,
    order_status VARCHAR(50)  NOT NULL,
    created_at   TIMESTAMP    NOT NULL
);
