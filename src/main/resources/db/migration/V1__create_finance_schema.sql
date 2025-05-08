-- Migration: Create initial finance schema

CREATE TABLE IF NOT EXISTS clients (
    client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS total_earnings (
    client_id BIGINT NOT NULL,
    earnings_q1 DOUBLE,
    earnings_q2 DOUBLE,
    earnings_q3 DOUBLE,
    earnings_q4 DOUBLE,
    time_period VARCHAR(50),
    time_id INT,
    complete_status TINYINT DEFAULT 0,
    CONSTRAINT fk_te_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS total_expenses (
    client_id BIGINT NOT NULL,
    expenses_q1 DOUBLE,
    expenses_q2 DOUBLE,
    expenses_q3 DOUBLE,
    expenses_q4 DOUBLE,
    time_period VARCHAR(50),
    time_id INT,
    complete_status TINYINT DEFAULT 0,
    CONSTRAINT fk_tex_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS financial_history (
    client_id BIGINT NOT NULL,
    time_period VARCHAR(50),
    revenue DOUBLE,
    expenses DOUBLE,
    net_revenue DOUBLE,
    time_id INT,
    CONSTRAINT fk_fh_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS expenses (
    client_id BIGINT NOT NULL,
    quarter_period INT,
    expense_type VARCHAR(100),
    expense_id INT,
    expense_amount DOUBLE,
    time_id INT,
    CONSTRAINT fk_exp_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS revenue (
    client_id BIGINT NOT NULL,
    quarter_period INT,
    sale_id INT,
    item_price DOUBLE,
    sales_quantity INT,
    time_id INT,
    item_name VARCHAR(255),
    CONSTRAINT fk_rev_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);
