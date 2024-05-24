create table admin
(
    id          int auto_increment
        primary key,
    account     varchar(16)                         not null comment '账号',
    password    varchar(99)                         not null comment '密码',
    username    varchar(16)                         not null comment '用户名',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    state       int       default 0                 not null comment '状态'
)
    engine = InnoDB
    row_format = DYNAMIC;

create table config
(
    `key` varchar(255) not null,
    value varchar(255) null
)
    engine = InnoDB;

create table notice
(
    notice_id      int auto_increment comment '通知id'
        primary key,
    notice_title   varchar(255) null comment '标题',
    notice_content text         null comment '内容',
    create_by      varchar(255) not null,
    create_time    datetime     null,
    remark         text         null
)
    engine = InnoDB;

create table product
(
    id    bigint auto_increment
        primary key,
    name  varchar(255)  not null comment '商品名称',
    cost  int           not null comment '需要的积分',
    image text          not null comment '商品图',
    stock int           not null comment '库存',
    state int default 1 not null
)
    engine = InnoDB;

create table school
(
    id    int auto_increment comment '学校id'
        primary key,
    name  varchar(18)   not null comment '学校名称',
    state int default 1 not null
)
    engine = InnoDB
    row_format = DYNAMIC;

create table dept
(
    id        int auto_increment comment '系别id'
        primary key,
    name      varchar(18)   not null comment '系别名称',
    school_id int           not null comment '学校id',
    state     int default 1 not null,
    constraint FK_DEPT_SCHOOL
        foreign key (school_id) references school (id)
)
    engine = InnoDB
    row_format = DYNAMIC;

create table class
(
    id        int auto_increment comment '班级id'
        primary key,
    name      varchar(18)   not null comment '班级名',
    school_id int           not null comment '学校id',
    dept_id   int           not null comment '系别id',
    state     int default 1 not null,
    constraint FK_CLASS_DEPT
        foreign key (dept_id) references dept (id),
    constraint FK_CLASS_SCHOOL
        foreign key (school_id) references school (id)
)
    engine = InnoDB
    row_format = DYNAMIC;

create table task_type
(
    id    int auto_increment
        primary key,
    name  varchar(255)  not null,
    state int default 1 not null comment '状态'
)
    engine = InnoDB;

create table user
(
    id          int auto_increment comment '用户id'
        primary key,
    student_id  varchar(16)                              not null comment '学号',
    password    varchar(99)                              not null comment '密码',
    phone       varchar(11)                              null comment '手机号',
    school_id   int                                      not null comment '学校id',
    dept_id     int                                      null comment '系别id',
    class_id    int                                      null comment '班级id',
    sex         int            default 0                 null comment '性别',
    username    varchar(16)                              null comment '用户名',
    create_time timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    balance     decimal(10, 2) default 0.00              null comment '余额',
    coin        double         default 0                 null comment '积分',
    state       int            default 0                 not null comment '状态',
    constraint FK_USER_CLASS
        foreign key (class_id) references class (id),
    constraint FK_USER_DEPT
        foreign key (dept_id) references dept (id),
    constraint FK_USER_SCHOOL
        foreign key (school_id) references school (id)
)
    engine = InnoDB
    row_format = DYNAMIC;

create table exchange_record
(
    id            bigint auto_increment
        primary key,
    user_id       int                                 not null,
    product_id    bigint                              not null,
    address       varchar(255)                        not null,
    name          varchar(255)                        not null,
    phone         varchar(11)                         not null,
    exchange_time timestamp default CURRENT_TIMESTAMP not null,
    state         int       default 0                 not null comment '状态',
    constraint exchange_record_product_id_fk
        foreign key (product_id) references product (id),
    constraint exchange_record_user_id_fk
        foreign key (user_id) references user (id)
)
    engine = InnoDB;

create index exchange_record_user_id_index
    on exchange_record (user_id);

create table task
(
    id              int auto_increment comment '任务id'
        primary key,
    publish_user_id int                                      not null comment '用户发布id',
    accept_user_id  int                                      null comment '接受任务用户id',
    user_school_id  int                                      not null comment '用户所在的学校id',
    task_type_id    int                                      not null comment '类型',
    image           text                                     null comment '图片url',
    reward          decimal(10, 2) default 0.00              null comment '任务奖励',
    coin            double         default 0                 null comment '积分',
    create_time     timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    pass_time       timestamp                                null comment '通过时间',
    order_time      timestamp                                null comment '接单时间',
    end_time        timestamp                                null comment '结束时间',
    task_title      varchar(50)                              not null comment '任务标题',
    task_context    varchar(255)                             not null comment '任务内容',
    state           int            default 0                 not null comment '状态',
    constraint FK_TASK_ACCEPT_USER
        foreign key (accept_user_id) references user (id),
    constraint FK_TASK_PUBLISH_USER
        foreign key (publish_user_id) references user (id),
    constraint FK_TASK_TASK_TYPE
        foreign key (task_type_id) references task_type (id),
    constraint FK_TASK_USER_SCHOOL
        foreign key (user_school_id) references school (id)
)
    engine = InnoDB
    row_format = DYNAMIC;

create table remark
(
    id         int auto_increment comment '评论id'
        primary key,
    star       int          null comment '评分',
    remark     varchar(255) null comment '评论',
    task_id    int          null comment '任务id',
    accept_id  int          null comment '接受任务用户id',
    publish_id int          null comment '发布任务用户id',
    constraint remark_task_accept_user_id_fk
        foreign key (accept_id) references task (accept_user_id)
            on delete cascade,
    constraint remark_task_id_fk
        foreign key (task_id) references task (id)
            on delete cascade,
    constraint remark_task_publish_user_id_fk
        foreign key (publish_id) references task (publish_user_id)
            on delete cascade
)
    engine = InnoDB;

create index user_student_id_fk
    on user (student_id);

