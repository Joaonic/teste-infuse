SET NAMES 'utf8' COLLATE 'utf8_unicode_ci';

CREATE TABLE IF NOT EXISTS infuse.orders
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary key, auto incremented for each request.',
    quantity          INT DEFAULT 1 COMMENT 'Quantity of products in the order. Default is 1 if not specified.',
    registration_date DATE           NOT NULL COMMENT 'Order registration date. Assumes current date if not specified.',
    total_price       DECIMAL(38, 2) NOT NULL COMMENT 'Total order value, calculated based on quantity and applicable discounts.',
    unit_price        DECIMAL(38, 2) NOT NULL COMMENT 'Unit value of the product.',
    control_number    VARCHAR(255)   NOT NULL COMMENT 'Order control number. It must be unique and only contain numbers.',
    customer_code     VARCHAR(255)   NOT NULL COMMENT 'Customer numeric code. There are clients with codes from 1 to 10.',
    name              VARCHAR(255)   NOT NULL COMMENT 'Product name.',
    CONSTRAINT UK_control_number UNIQUE (control_number),
    CONSTRAINT CHK_control_number CHECK (control_number REGEXP '^[0-9]+$'),
    CONSTRAINT CHK_customer_code CHECK (customer_code REGEXP '^[0-9]+$')
);
