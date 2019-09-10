drop table if exists tb_access;
create table tb_access
(
    id        int auto_increment primary key,
    code      varchar(50)  not null,
    name      varchar(50)  not null,
    parent_id int          null,
    url       varchar(255) null,
    type      int          null,
    method    int          null
);

drop table if exists tb_email_code;
create table tb_email_code
(
    id   int        not null primary key,
    code varchar(6) not null
);

drop table if exists tb_role;
create table tb_role
(
    id   int auto_increment primary key,
    code varchar(50) not null,
    name varchar(50) not null
);

drop table if exists tb_role_access;
create table tb_role_access
(
    id        int auto_increment primary key,
    role_id   int not null,
    access_id int not null
);

drop table if exists tb_user;
create table tb_user
(
    id       int auto_increment primary key,
    username varchar(50)            not null,
    password varchar(255)           not null,
    gender   varchar(1) default '0' not null,
    nickname varchar(50)            null,
    phone    varchar(12)            null,
    email    varchar(50)            not null,
    avatar   varchar(255)           null,
    status   varchar(1) default '0' not null
);

drop table if exists tb_user_role;
create table tb_user_role
(
    id      int auto_increment primary key,
    user_id int not null,
    role_id int not null
);