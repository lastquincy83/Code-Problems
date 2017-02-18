USE library;

DELETE FROM book;

DROP TABLE book;

CREATE TABLE book (
  ISBN VARCHAR(50),
  title VARCHAR(30),
  author VARCHAR(30),
  rented BOOLEAN,
  overdue BOOLEAN,
  PRIMARY KEY (ISBN)
);