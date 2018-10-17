CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  hash_password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE tokens (
  id BIGSERIAL PRIMARY KEY,
  value VARCHAR(255) NOT NULL,
  user_id BIGINT NOT NULL REFERENCES users(id),
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE flights (
  id BIGSERIAL PRIMARY KEY,
  serial_number BIGINT NOT NULL,
  date_time TIMESTAMPTZ NOT NULL,
  destination_from VARCHAR(255) NOT NULL,
  destination_to VARCHAR(255) NOT NULL,
  price NUMERIC NOT NULL,
  num_of_seats INTEGER NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE flight_discounts (
  id BIGSERIAL PRIMARY KEY,
  value NUMERIC NOT NULL,
  flight_id BIGINT NOT NULL REFERENCES flights(id),
  from_date TIMESTAMPTZ NOT NULL,
  to_date TIMESTAMPTZ NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE user_discounts (
  id BIGSERIAL PRIMARY KEY,
  value NUMERIC NOT NULL,
  user_id BIGINT NOT NULL REFERENCES users(id),
  from_date TIMESTAMPTZ NOT NULL,
  to_date TIMESTAMPTZ NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE tickets (
  id BIGSERIAL PRIMARY KEY,
  serial_number BIGINT NOT NULL,
  flight_serial_number BIGINT NOT NULL REFERENCES flights(id),
  user_id BIGINT NOT NULL REFERENCES users(id),
  place VARCHAR(255) NOT NULL,
  price NUMERIC NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE subscribes (
  id BIGSERIAL PRIMARY KEY,
  destination_from VARCHAR(255) NOT NULL,
  destination_to VARCHAR(255) NOT NULL,
  price_from NUMERIC NOT NULL,
  price_to NUMERIC NOT NULL,
  date_from TIMESTAMPTZ NOT NULL,
  date_to TIMESTAMPTZ NOT NULL,
  user_id BIGINT NOT NULL REFERENCES users(id),
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
)