CREATE TABLE user_table (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  hash_password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE token (
  id BIGSERIAL PRIMARY KEY,
  value VARCHAR(255) NOT NULL,
  user_id BIGINT NOT NULL REFERENCES user_table(id),
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE flight (
  id BIGSERIAL PRIMARY KEY,
  serial_number BIGINT UNIQUE NOT NULL,
  date_time TIMESTAMPTZ NOT NULL,
  destination_from VARCHAR(255) NOT NULL,
  destination_to VARCHAR(255) NOT NULL,
  price NUMERIC NOT NULL,
  num_of_seats INTEGER NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE flight_discount (
  id BIGSERIAL PRIMARY KEY,
  value NUMERIC NOT NULL,
  flight_id BIGINT NOT NULL REFERENCES flight(id),
  from_date TIMESTAMPTZ NOT NULL,
  to_date TIMESTAMPTZ NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE user_discount (
  id BIGSERIAL PRIMARY KEY,
  value NUMERIC NOT NULL,
  user_id BIGINT NOT NULL REFERENCES user_table(id),
  from_date TIMESTAMPTZ NOT NULL,
  to_date TIMESTAMPTZ NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE ticket (
  id BIGSERIAL PRIMARY KEY,
  serial_number BIGINT UNIQUE NOT NULL,
  flight_id BIGINT NOT NULL REFERENCES flight(id),
  user_id BIGINT NOT NULL REFERENCES user_table(id),
  place VARCHAR(255) NOT NULL,
  price NUMERIC NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
);

CREATE TABLE subscribe (
  id BIGSERIAL PRIMARY KEY,
  destination_from VARCHAR(255) NOT NULL,
  destination_to VARCHAR(255) NOT NULL,
  price_from NUMERIC NOT NULL,
  price_to NUMERIC NOT NULL,
  date_from TIMESTAMPTZ NOT NULL,
  date_to TIMESTAMPTZ NOT NULL,
  user_id BIGINT NOT NULL REFERENCES user_table(id),
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ
)