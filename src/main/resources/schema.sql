CREATE TABLE snippet (
    id INT PRIMARY KEY,
    language VARCHAR(255) NOT NULL,
    code TEXT NOT NULL
);

CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    hashed_password VARCHAR(255) NOT NULL
)