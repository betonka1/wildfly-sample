create table groups
(
    id      bigserial not null
        constraint group_pkey
            primary key,
    created timestamp,
    name    varchar(255),
    version integer
);

alter table groups
    owner to root;

create table users
(
    id        bigserial not null
        constraint users_pkey
            primary key,
    created   timestamp,
    email     varchar(255),
    user_name varchar(255),
    type      varchar(255),
    version   integer
);

alter table users
    owner to root;

create table groups_users
(
    user_id  integer not null
        constraint users_to_groups_user_fk
            references users
            on delete cascade,
    group_id integer not null
        constraint users_to_groups_groups_fk
            references groups
            on delete cascade
);

alter table groups_users
    owner to root;
