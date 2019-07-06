create table room
(
    id       uuid primary key,
    price    int     not null,
    capacity tinyint not null
);

create table lodging
(
    id               uuid primary key,
    room_id          uuid    not null references room,
    start_on         date    not null,
    number_of_guests tinyint not null,
    number_of_nights tinyint
);

create table guest
(
    id      uuid primary key,
    name    varchar2(63)  not null,
    address varchar2(127) not null,
    tel     varchar2(15)  not null
);

create table reservation
(
    id         uuid primary key,
    guest_id   uuid not null references guest,
    lodging_id uuid not null references lodging
);