CREATE TABLE requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theType VARCHAR(50) NOT NULL,
    poriority VARCHAR(20) NOT NULL,
    description VARCHAR(500) NOT NULL,
    approval VARCHAR(10) CHECK (approval IN ('TRUE', 'FALSE')),
    comments VARCHAR(100)
);