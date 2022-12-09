CREATE TABLE IF NOT EXISTS my_user2 (
                                        id int(255) primary key auto_increment,
                                        first_name varchar(255) not null,
                                        last_name varchar(255) not null,
                                        email varchar(500) not null,
                                        phone_number varchar(255) not null,
                                        password varchar(555) not null
);
CREATE TABLE IF NOT EXISTS my_user (
                                       id int(255) primary key auto_increment,
                                       first_name varchar(255) not null,
                                       last_name varchar(255) not null,
                                       email varchar(500) not null,
                                       phone_number varchar(255) not null,
                                       password varchar(555) not null
);