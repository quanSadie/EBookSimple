CREATE DATABASE EBOOK;
USE EBOOK;


CREATE TABLE books(
book_id int primary key identity(1,1),
title varchar(20),
cover varchar(100),
price float,
description varchar(500),
rating smallint,
isbn char(13),
readLink varchar(100)
)

CREATE TABLE author (
author_id int primary key identity(1,1),
author_name varchar(30),
)

CREATE TABLE authors_books(
author_id int FOREIGN KEY REFERENCES author(author_id),
book_id int FOREIGN KEY REFERENCES books(book_id),
CONSTRAINT pk_authors_books PRIMARY KEY(author_id, book_id)
)

CREATE TABLE genres (
genre_id int primary key identity(1,1),
genre_name varchar(30)
)

CREATE TABLE books_genres(
genre_id int FOREIGN KEY REFERENCES genres(genre_id),
book_id int FOREIGN KEY REFERENCES books(book_id),
CONSTRAINT pk_genres_books PRIMARY KEY(genre_id, book_id)
)

CREATE TABLE users(
user_id int primary key identity(1,1),
userEmail varchar(20),
username varchar(20),
password varchar(20),
role int,
fullname varchar(30),
statusAcc int
)

CREATE TABLE users_books(
user_id int FOREIGN KEY REFERENCES users(user_id),
book_id int FOREIGN KEY REFERENCES books(book_id),
CONSTRAINT pk_users_books PRIMARY KEY(user_id, book_id)
)
CREATE TABLE cmtOfBook(
comment_id int primary key identity(1,1),
user_id int FOREIGN KEY REFERENCES users(user_id),
book_id int FOREIGN KEY REFERENCES books(book_id),
comment varchar(2000)
)