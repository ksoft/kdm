INSERT INTO user (username,email, password, activated) VALUES ('admin', 'admin@mail.me', '08f176fe719d1b1bf18c273ca4770303', true);
INSERT INTO user (username,email, password, activated) VALUES ('user', 'user@mail.me', 'ede110d2b5e42079f7468db435c339f0', true);
INSERT INTO user (username,email, password, activated) VALUES ('rajith', 'rajith@abc.com', '28207cdfb01842559aec4e3b1c1f1e8f', true);

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (username,authority) VALUES ('rajith', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('user', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_ADMIN');