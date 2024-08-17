CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_permissions (
    user_id BIGINT NOT NULL,
    permissions_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, permissions_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (permissions_id) REFERENCES permissions(id)
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER'), ('ROLE_MANAGER');

INSERT INTO permissions (name) VALUES
    ('CREATE'),
    ('READ'),
    ('UPDATE'),
    ('DELETE');

 INSERT INTO user_roles (user_id, role_id)
 select 5, id FROM roles;

 INSERT INTO user_permissions (user_id, permissions_id)
  select 5, id FROM permissions;