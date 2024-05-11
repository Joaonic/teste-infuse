SET NAMES 'utf8' COLLATE 'utf8_unicode_ci';

CREATE TABLE IF NOT EXISTS infuse.orders
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Chave primária, auto incrementada para cada pedido.',
    quantity          INT DEFAULT 1 COMMENT 'Quantidade de produtos no pedido. Default é 1 se não especificado.',
    registration_date DATE           NOT NULL COMMENT 'Data de cadastro do pedido. Assume a data atual se não especificado.',
    total_price       DECIMAL(38, 2) NOT NULL COMMENT 'Valor total do pedido, calculado com base na quantidade e descontos aplicáveis.',
    unit_price        DECIMAL(38, 2) NOT NULL COMMENT 'Valor unitário do produto.',
    control_number    VARCHAR(255)   NOT NULL COMMENT 'Número de controle do pedido. Deve ser único e só contém números.',
    customer_code     VARCHAR(255)   NOT NULL COMMENT 'Código numérico do cliente. Existem clientes com códigos de 1 a 10.',
    name              VARCHAR(255)   NOT NULL COMMENT 'Nome do produto.',
    CONSTRAINT UK_control_number UNIQUE (control_number),
    CONSTRAINT CHK_control_number CHECK (control_number REGEXP '^[0-9]+$'),
    CONSTRAINT CHK_customer_code CHECK (customer_code REGEXP '^[0-9]+$')
);
