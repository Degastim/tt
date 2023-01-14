CREATE TABLE user_roles
(
    user_role_id   BIGSERIAL      NOT NULL,
    user_role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_role_id)
);
CREATE TABLE users
(
    user_id          BIGSERIAL      NOT NULL ,
    user_email       VARCHAR(40) NOT NULL,
    password         VARCHAR(60) NOT NULL,
    is_active        Boolean         NOT NULL,
    user_role        BIGINT      NOT NULL,
    FOREIGN KEY (user_role) REFERENCES user_roles(user_role_id),
    PRIMARY KEY (user_id)
);
CREATE TABLE permissions
(
    permission_id   BIGSERIAL      NOT NULL ,
    permission_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (permission_id)
);

CREATE TABLE user_roles_permissions
(
    user_role_permission_id BIGSERIAL NOT NULL ,
    user_role_id            BIGINT NOT NULL,
    permission_id           BIGINT NOT NULL,
    FOREIGN KEY (user_role_id) REFERENCES user_roles (user_role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions (permission_id),
    PRIMARY KEY (user_role_permission_id)
);

INSERT INTO permissions(permission_id,permission_name) VALUES (1,'users:create');
INSERT INTO permissions(permission_id,permission_name) VALUES (2,'users:delete');

INSERT INTO user_roles(user_role_id,user_role_name) VALUES (1,'USER');
INSERT INTO user_roles(user_role_id,user_role_name) VALUES (2,'ADMIN');

INSERT INTO user_roles_permissions(user_role_id,permission_id) VALUES (2,1);
INSERT INTO user_roles_permissions(user_role_id,permission_id) VALUES (2,2);
INSERT INTO users (user_id, user_email, password,is_active,user_role)
VALUES (1, 'zhenya@gmail.com','$2a$12$m8/VckkBoNwIfn3UfGHL4.IiPs8.W8Tjsx8GgsYsbVF2SrEzQfCe',true,2);