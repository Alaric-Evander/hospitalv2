DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE if not exists users (
                       username VARCHAR(50) NOT NULL PRIMARY KEY,
                       password VARCHAR(500) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE if not exists authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);