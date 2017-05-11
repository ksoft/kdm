INSERT INTO user (username,email, password, activated) VALUES ('admin', 'admin@mail.me', '21232f297a57a5a743894a0e4a801fc3', true);
INSERT INTO user (username,email, password, activated) VALUES ('user', 'user@mail.me', 'f777215f640fad06c016944391fceb46', true);
INSERT INTO user (username,email, password, activated) VALUES ('rajith', 'rajith@abc.com', 'ee11cbb19052e40b07aac0ca060c23ee', true);

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (username,authority) VALUES ('rajith', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('user', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_ADMIN');