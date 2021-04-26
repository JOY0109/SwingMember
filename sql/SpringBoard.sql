CREATE TABLE  MENUS
(
     MENU_ID    CHAR(6)       NOT NULL PRIMARY KEY
   , MENU_NAME  VARCHAR2(100) NOT NULL
   , MENU_SEQ   NUMBER(5, 0)  NOT NULL
);

-- 초기 data 등록
INSERT INTO  MENUS (MENU_ID, MENU_NAME, MENU_SEQ)
 VALUES  ( 'MENU01', 'JAVA', 1  );
commit; 

 -- 게시물 목록
CREATE TABLE MBOARD
(
      IDX        NUMBER(5, 0)    NOT NULL PRIMARY KEY
    , MENU_ID    CHAR(6)         NOT NULL
    , TITLE      VARCHAR2(200)   NOT NULL
    , CONT       VARCHAR2(4000)  NOT NULL
    , WRITER     VARCHAR2(50)    NOT NULL
    , REGDATE    VARCHAR2(50)    NOT NULL
    , READCOUNT  NUMBER(5, 0)    NOT NULL
    
    , BNUM       NUMBER(5, 0)    NOT NULL
    , LVL        NUMBER(5, 0)    NOT NULL
    , STEP       NUMBER(5, 0)    NOT NULL
    , NREF       NUMBER(5, 0)    NOT NULL
);

-- PKG_PDS
-- 칼럼 추가 (delnum) 삭제 표시 DELNUM: 1 -> 삭제된 자료 
ALTER TABLE   MBOARD 
  ADD ( DELNUM  NUMBER(1, 0) );

-- 추가된 칼럼에 초기값 설정  
UPDATE  MBOARD
  SET  DELNUM = 0;
commit;  

  -- 게시글(MBoard) 과 관련된 파일목록
CREATE TABLE  FILES
(
    FILE_NUM    NUMBER(5, 0)  NOT NULL
   , IDX        NUMBER(5, 0)  NOT NULL
   , FILENAME   VARCHAR2(300) NOT NULL
   , FILEEXT    VARCHAR2(30)  NOT NULL
   , SFILENAME  VARCHAR2(300) NOT NULL   
   , CONSTRAINT  FILES_PK   PRIMARY KEY
     (     
         FILE_NUM,
         IDX
     )
   , CONSTRAINT  FK_MBOARD_FILES_IDX
      FOREIGN KEY (IDX)
      REFERENCES  MBOARD (IDX)
      --   ON DELETE CASCADE
);

-- PROCEDRE PROC_PDS_INSERT 안에서 사용할 
-- 사용자 타입을 정의하기 위해 FILE_ARRAY 타입을 생성
--  IN_FILENAME   IN    FILE_ARRAY,
-- CREATE TYPE -  새로운타입 정의

-- oracle plsql 문법에 is table of : 1차원 배열로 해석
-- oracle 배열 : 크기를 정하지 않은 뱌열
CREATE OR REPLACE TYPE FILE_ARRAY
    AS TABLE OF
        VARCHAR2(4000);
        
-- SEQUENCE : 자동번호 증가 - 글번호
--CREATE SEQUENCE  BRDSEQ;       



CREATE TABLE USER_TBL(
    USERID      VARCHAR2(20) PRIMARY KEY
    ,USERPASS   VARCHAR2(20) NOT NULL
    ,USERNAME   VARCHAR2(60) NOT NULL
    ,USERPOINT  NUMBER(10,0) DEFAULT 1000
);

INSERT INTO user_tbl VALUES ('TEST01','1111','테스트1',1000);
INSERT INTO user_tbl VALUES ('TEST02','1111','테스트2',1000);
INSERT INTO user_tbl VALUES ('TEST03','1111','테스트3',1000);

commit;

SELECT
    userid,
    userpass,
    username,
    userpoint
FROM
    user_tbl;

select * from MBOARD;

;


