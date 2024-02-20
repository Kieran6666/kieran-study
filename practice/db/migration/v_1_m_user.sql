create table m_user(
           id              int(9) primary key auto_increment not null  comment '主键ID',
           nick_name       varchar(50)                       not null  comment '昵称',
           age             int(2)                            not null comment '年龄'
) ENGINE = InnoDB COMMENT '用户表' charset = utf8mb4;
