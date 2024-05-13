DELIMITER //
CREATE PROCEDURE IF NOT EXISTS InsertOrder()
BEGIN
    DECLARE v_quantity INT;
    DECLARE v_unit_price DECIMAL(38,2);
    DECLARE v_total_price DECIMAL(38,2);
    DECLARE v_control_number VARCHAR(255);
    DECLARE v_customer_code VARCHAR(255);
    DECLARE v_name VARCHAR(255);
    DECLARE v_registration_date DATE;

    SET v_quantity = FLOOR(RAND() * 20 + 1);
    SET v_unit_price = RAND() * 90 + 10;
    SET v_registration_date = CURDATE() - INTERVAL FLOOR(RAND() * 730) DAY;
    SET v_control_number = LPAD(FLOOR(RAND() * 9000000000 + 1000000000), 10, '0');
    SET v_customer_code = LPAD(FLOOR(RAND() * 10 + 1), 3, '0');
    SET v_name = CONCAT('Product ', FLOOR(RAND() * 100 + 1));

    IF v_quantity > 10 THEN
        SET v_total_price = ROUND(v_quantity * v_unit_price * 0.90, 2); -- 10% discount
    ELSEIF v_quantity > 5 THEN
        SET v_total_price = ROUND(v_quantity * v_unit_price * 0.95, 2); -- 5% discount
    ELSE
        SET v_total_price = ROUND(v_quantity * v_unit_price, 2); -- No discount
    END IF;

    INSERT INTO infuse.orders (quantity, registration_date, total_price, unit_price, control_number, customer_code, name)
    VALUES (v_quantity, v_registration_date, v_total_price, v_unit_price, v_control_number, v_customer_code, v_name);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE IF NOT EXISTS InsertMultipleOrders()
BEGIN
    DECLARE v_counter INT DEFAULT 0;

    START TRANSACTION;
    WHILE v_counter < 50 DO
            CALL InsertOrder();
            SET v_counter = v_counter + 1;
        END WHILE;
    COMMIT;
END //
DELIMITER ;

CALL InsertMultipleOrders();