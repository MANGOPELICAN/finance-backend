-- V3__create_company_table.sql

CREATE TABLE company (
  id        BIGSERIAL PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL
);
