-- Migration: Create initial finance schema

CREATE TABLE IF NOT EXISTS clients (
    client_id BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS total_earnings (
    client_id      BIGINT    NOT NULL,
    earnings_q1    DOUBLE PRECISION,
    earnings_q2    DOUBLE PRECISION,
    earnings_q3    DOUBLE PRECISION,
    earnings_q4    DOUBLE PRECISION,
    time_period    VARCHAR(50),
    time_id        INT,
    complete_status SMALLINT DEFAULT 0,
    CONSTRAINT fk_te_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS total_expenses (
    client_id      BIGINT    NOT NULL,
    expenses_q1    DOUBLE PRECISION,
    expenses_q2    DOUBLE PRECISION,
    expenses_q3    DOUBLE PRECISION,
    expenses_q4    DOUBLE PRECISION,
    time_period    VARCHAR(50),
    time_id        INT,
    complete_status SMALLINT DEFAULT 0,
    CONSTRAINT fk_tex_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS financial_history (
    client_id   BIGINT    NOT NULL,
    time_period VARCHAR(50),
    revenue     DOUBLE PRECISION,
    expenses    DOUBLE PRECISION,
    net_revenue DOUBLE PRECISION,
    time_id     INT,
    CONSTRAINT fk_fh_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS expenses (
    client_id     BIGINT    NOT NULL,
    quarter_period INT,
    expense_type   VARCHAR(100),
    expense_id     INT,
    expense_amount DOUBLE PRECISION,
    time_id        INT,
    CONSTRAINT fk_exp_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE IF NOT EXISTS revenue (
    client_id      BIGINT    NOT NULL,
    quarter_period INT,
    sale_id        INT,
    item_price     DOUBLE PRECISION,
    sales_quantity INT,
    time_id        INT,
    item_name      VARCHAR(255),
    CONSTRAINT fk_rev_client FOREIGN KEY (client_id) REFERENCES clients(client_id)
);
