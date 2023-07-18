-- Insert statement 1
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (1, 'user1', 'user1@example.com', 'password1');

-- Insert statement 2
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (2, 'user2', 'user2@example.com', 'password2');

-- Insert statement 3
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (3, 'user3', 'user3@example.com', 'password3');

-- Insert statement 4
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (4, 'user4', 'user4@example.com', 'password4');

-- Insert statement 5
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (5, 'user5', 'user5@example.com', 'password5');

-- Insert statement 6
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (6, 'user6', 'user6@example.com', 'password6');

-- Insert statement 7
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (7, 'user7', 'user7@example.com', 'password7');

-- Insert statement 8
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (8, 'user8', 'user8@example.com', 'password8');

-- Insert statement 9
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (9, 'user9', 'user9@example.com', 'password9');

-- Insert statement 10
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (10, 'user10', 'user10@example.com', 'password10');

-- Insert statement 11
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (11, 'user11', 'user11@example.com', 'password11');

-- Insert statement 12
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (12, 'user12', 'user12@example.com', 'password12');

-- Insert statement 13
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (13, 'user13', 'user13@example.com', 'password13');

-- Insert statement 14
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (14, 'user14', 'user14@example.com', 'password14');

-- Insert statement 15
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (15, 'user15', 'user15@example.com', 'password15');

-- Insert statement 16
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (16, 'user16', 'user16@example.com', 'password16');

-- Insert statement 17
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (17, 'user17', 'user17@example.com', 'password17');

-- Insert statement 18
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (18, 'user18', 'user18@example.com', 'password18');

-- Insert statement 19
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (19, 'user19', 'user19@example.com', 'password19');

-- Insert statement 20
INSERT INTO USERS (ID, USERNAME, EMAIL, PASSWORD)
VALUES (20, 'user20', 'user20@example.com', 'password20');

-- Insert statement for ADMIN role
INSERT INTO ROLES (ID,ROLE_TYPE) VALUES (1,'ADMIN');

-- Insert statement for GUEST role
INSERT INTO ROLES (ID,ROLE_TYPE) VALUES (2,'GUEST');

-- Assigning ADMIN role to user1
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (1, 1);

-- Assigning GUEST role to user1
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (1, 2);

-- Assigning GUEST role to user2
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (2, 2);

-- Assigning ADMIN role to user3
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (3, 1);

-- Assigning GUEST role to user4
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (4, 2);

-- Assigning ADMIN role to user5
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (5, 1);

-- Assigning GUEST role to user6
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (6, 2);

-- Assigning GUEST role to user7
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (7, 2);

-- Assigning ADMIN role to user8
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (8, 1);

-- Assigning ADMIN role to user9
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (9, 1);

-- Assigning GUEST role to user10
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (10, 2);

-- Assigning GUEST role to user11
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (11, 2);

-- Assigning ADMIN role to user12
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (12, 1);

-- Assigning GUEST role to user13
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (13, 2);

-- Assigning GUEST role to user14
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (14, 2);

-- Assigning ADMIN role to user15
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (15, 1);

-- Assigning ADMIN role to user16
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (16, 1);

-- Assigning GUEST role to user17
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (17, 2);

-- Assigning GUEST role to user18
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (18, 2);

-- Assigning ADMIN role to user19
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (19, 1);

-- Assigning GUEST role to user20
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (20, 2);

