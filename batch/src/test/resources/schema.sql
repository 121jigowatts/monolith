CREATE TABLE IF NOT EXISTS address (
    prefecture_code VARCHAR(2),
    prefecture      VARCHAR(4),
    prefecture_kana VARCHAR(10),
    prefecture_roma VARCHAR(20),
    city_code       VARCHAR(5),
    city            VARCHAR(10),
    city_kana       VARCHAR(30),
    city_roma       VARCHAR(60),
    street_code     VARCHAR(12),
    street          VARCHAR(30),
    latitude        VARCHAR(18),
    longitude       VARCHAR(18),
    create_at       TIMESTAMP,
    create_by       VARCHAR(20),
    update_at       TIMESTAMP,
    update_by       VARCHAR(20),
    PRIMARY KEY(prefecture_code, city_code, street_code)
);
