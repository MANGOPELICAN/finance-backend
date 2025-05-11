-- 1) Rename the PK column
ALTER TABLE clients RENAME COLUMN client_id TO id;

-- 2) (Optional) Rename the sequence for clarity
ALTER SEQUENCE clients_client_id_seq RENAME TO clients_id_seq;

-- 3) Update the default on the renamed column
ALTER TABLE clients
  ALTER COLUMN id
  SET DEFAULT nextval('clients_id_seq'::regclass);
