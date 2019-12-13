-- drop table if exists user;

create table if not exists user (
    id int auto_increment,
    account varchar(256) not null,
    password varchar(256) not null,
    constraint user_pk primary key (id)
);

/*
insert into user(id, account, password)
values
       (0, 'root', 'root'),
       (1, 'kasei', 'kasei');
*/

