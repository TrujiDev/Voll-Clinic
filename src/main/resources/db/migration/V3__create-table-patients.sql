CREATE TABLE patients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    document VARCHAR(14) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    street VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    number VARCHAR(10),
    active TINYINT NOT NULL,

    PRIMARY KEY (id)
);