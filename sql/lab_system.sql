drop database if exists lab_system;
create database lab_system
    default character set utf8mb4 collate utf8mb4_general_ci;

use lab_system;

DROP TABLE IF EXISTS rights;
CREATE TABLE rights(
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(20) NOT NULL,
                       path VARCHAR(100) NOT NULL,
                       icon VARCHAR(100) NOT NULL,
                       parent_id INT DEFAULT '0',
    -- 父级菜单ID
                       is_leaf TINYINT DEFAULT '0'  -- 是否是叶子菜单。0-否;1-是
);
INSERT INTO rights (title, path,icon) VALUES ('首页','/index','HomeFilled');
INSERT INTO rights (title, path,icon) VALUES ('用户管理','/user-manage', 'User');
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('用户列表','/user-manage/list','List',2,1);
INSERT INTO rights (title, path,icon) VALUES ('权限管理','/right-manage', 'Key' );
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('角色列表','/right-manage/rolelist','List', 4, 1);
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('权限列表','/right-manage/rightlist','List',4,1);
INSERT INTO rights (title,path,icon) VALUES ('课室管理','/lab-manage','OfficeBuilding');
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('课室列表','/lab-manage/lablist','List',7,1);
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('添加课室','/lab-manage/addlab','List',7,1);
INSERT INTO rights (title,path,icon) VALUES ('预约管理','/book-manage','UploadFilled');

INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('审核列表','/book-manage/auditlist','List',10,1);
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('预约列表','/book-manage/booklist','List',10,1);
INSERT INTO rights (title, path,icon, parent_id, is_leaf) VALUES ('预约课室','/book-manage/addbook','List',10,1);

CREATE TABLE roles (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       roleName VARCHAR(20) NOT NULL,
                       roleType INT NOT NULL,
                       rights JSON NOT NULL
);
INSERT INTO
    roles (roleName, roleType, rights)
VALUES
    (
        '管理员',
        1,
        '[
          "/index",
          "/user-manage",
          "/user-manage/list",
          "/right-manage",
          "/right-manage/rolelist",
          "/right-manage/rightlist",
          "/lab-manage",
          "/lab-manage/lablist",
          "/lab-manage/addlab",
          "/book-manage",
          "/book-manage/auditlist",
          "/book-manage/booklist",
          "/book-manage/addbook"
        ]'
    );
INSERT INTO
    roles (roleName, roleType, rights)
VALUES
    (
        '用户',
        2,
        '[
          "/index",
          "/book-manage",
          "/book-manage/booklist",
          "/book-manage/addbook"
        ]'
    ),
    (
        '教师',
        3,
        '[
          "/index",
          "/user-manage",
          "/user-manage/list",
          "/lab-manage",
          "/lab-manage/lablist",
          "/lab-manage/addlab",
          "/book-manage",
          "/book-manage/auditlist",
          "/book-manage/booklist",
          "/book-manage/addbook"
        ]'
    )
;


CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(20) NOT NULL,
                       password VARCHAR(20) NOT NULL,
                       roleId INT NOT NULL,
                       is_default INT DEFAULT '0',
                       FOREIGN KEY (roleId) REFERENCES roles(id)
);
INSERT INTO users (username, password,roleId, is_default) VALUES ('admin','123',1,1);
INSERT INTO users (username, password,roleId) VALUES ('xzj','123',2);
INSERT INTO users (username, password,roleId) VALUES ('teacher','123',3);

CREATE TABLE labs (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(20) NOT NULL,
                      capacity INT DEFAULT '0',
                      lab_type INT DEFAULT '0',
                      college_type INT DEFAULT '0',
                      x INT DEFAULT '0',
                      y INT DEFAULT '0'
);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'华为云实验室','100',1,2,500,500);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'体育馆','100',5,5,428,523);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'云计算实验室','10',3,1,465,511);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'图书馆','1000',1,6,602,453);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'沙盘模拟实验室','50',5,4,630,487);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'云计算实验室','50',5,4,567,477);
INSERT INTO labs (id, title,capacity,lab_type,college_type,x,y) VALUES (null,'7号实验室','10',2,3,588,528);



CREATE TABLE books (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       lab_id INT DEFAULT '0',
                       book_time DATE,
                       book_class INT DEFAULT '0',
                       book_reason VARCHAR(100) NOT NULL,
                       book_username VARCHAR(20) NOT NULL,
                       book_state INT DEFAULT '0'
);
-- 0 审核中，1 审核通过， 2 被驳回
INSERT INTO books (lab_id,book_time,book_class,book_reason,book_username,book_state) VALUES (1,'2024-02-29',3,"训练","xzj",0);
INSERT INTO books (lab_id,book_time,book_class,book_reason,book_username,book_state) VALUES (3,'2024-02-27',3,"aaa","xzj",1);
INSERT INTO books (lab_id,book_time,book_class,book_reason,book_username,book_state) VALUES (3,'2024-02-26',3,"bbbbbbbbbb","xzj",2);
INSERT INTO books (lab_id,book_time,book_class,book_reason,book_username,book_state) VALUES (2,'2024-02-28',3,"ccc","admin",0);
INSERT INTO books (lab_id,book_time,book_class,book_reason,book_username,book_state) VALUES (1,'2024-02-28',3,"aaa","admin",1);
INSERT INTO books (lab_id,book_time,book_class,book_reason,book_username,book_state) VALUES (1,'2024-02-28',3,"aaa","aaa",1);


