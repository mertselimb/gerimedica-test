CREATE TABLE csv (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  source VARCHAR(255) NOT NULL,
  codeListCode VARCHAR(255) NOT NULL,
  code VARCHAR(255) UNIQUE NOT NULL,
  displayValue VARCHAR(255),
  longDescription VARCHAR(255),
  fromDate DATE,
  toDate DATE,
  sortingPriority INT
);