CREATE TABLE BOOK (
  BOOK_ID BIGINT AUTO_INCREMENT,
  ISBN VARCHAR(128) PRIMARY KEY,
  TITLE VARCHAR(128) NOT NULL,
  AUTHOR VARCHAR(256) NOT NULL,
  STOCK_COPIES INTEGER NOT NULL
);