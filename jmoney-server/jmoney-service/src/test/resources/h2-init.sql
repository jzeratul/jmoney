DROP TABLE IF EXISTS JUSER;
CREATE TABLE JUSER (
      USERID NUMBER(10, 0) NOT NULL AUTO_INCREMENT
    , USERNAME VARCHAR (60) NOT NULL
    , PASSWORD VARCHAR (100) NOT NULL
    , CREATED_AT DATETIME NOT NULL
    , PRIMARY KEY(USERNAME)
);

DROP TABLE IF EXISTS JAR;
CREATE TABLE JAR (
      JARID NUMBER(10, 0) NOT NULL AUTO_INCREMENT
    , USERID NUMBER(10, 0) NOT NULL
    , NAME VARCHAR (60) NOT NULL
    , PERCENT DECIMAL(4, 2) NOT NULL
    , VARIANT VARCHAR (60) NOT NULL
    , CREATED_AT DATETIME NOT NULL
    , PRIMARY KEY(USERID, NAME)
    , CONSTRAINT JAR_JUSER_FK FOREIGN KEY (USERID) REFERENCES JUSER(USERID)
);

DROP TABLE IF EXISTS PAYMENT;
CREATE TABLE PAYMENT (
    PAYMENTID NUMBER(10, 0) NOT NULL PRIMARY KEY AUTO_INCREMENT
  , JARID NUMBER(10, 0) NOT NULL
  , REASON VARCHAR (50) NOT NULL
  , AMOUNT DECIMAL(10, 2) NOT NULL
  , PAYMENT_DATE DATETIME NOT NULL
  , CREATED_AT DATETIME NOT NULL
  , CONSTRAINT PAYMENT_JAR_FK FOREIGN KEY (JARID) REFERENCES JAR(JARID)
);

DROP TABLE IF EXISTS INCOME;
CREATE TABLE INCOME (
      INCOMEID NUMBER(10, 0) NOT NULL PRIMARY KEY AUTO_INCREMENT
    , USERID NUMBER(10, 0) NOT NULL
    , SOURCE VARCHAR (200) NOT NULL
    , AMOUNT DECIMAL(10, 2) NOT NULL
    , INCOME_DATE DATETIME NOT NULL
    , CREATED_AT DATETIME NOT NULL
    , CONSTRAINT INCOME_JUSER_FK FOREIGN KEY (USERID) REFERENCES JUSER(USERID)
);
