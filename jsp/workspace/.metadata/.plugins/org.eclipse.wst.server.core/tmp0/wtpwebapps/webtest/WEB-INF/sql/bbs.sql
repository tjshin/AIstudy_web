CREATE TABLE bbs ( 
  bbsno    NUMBER(7)       NOT NULL,   -- 글 일련 번호, -9999999 ~ +9999999 
  wname   VARCHAR(20)    NOT NULL,   -- 글쓴이 
  title        VARCHAR(100)  NOT NULL,   -- 제목(*) 
  content   VARCHAR(4000) NOT NULL,  -- 글 내용 
  passwd   VARCHAR(15)     NOT NULL,  -- 비밀 번호 
  viewcnt   NUMBER(5)       DEFAULT 0,  -- 조회수, 기본값 사용 
  wdate     DATE               NOT NULL,  -- 등록 날짜, sysdate 
  grpno     NUMBER(7)       DEFAULT 0, -- 부모글 번호 
  indent    NUMBER(2)       DEFAULT 0,  -- 답변여부,답변의 깊이
  ansnum  NUMBER(5)       DEFAULT 0,  -- 답변 순서 
  PRIMARY KEY (bbsno)  
); 

-- c(create, insert)
-- 일렬번호 생성
select nvl(max(bbsno),0)+1 as max from bbs;
select nvl(max(bbsno),0)+1 as bbsno from bbs;

insert into bbs(bbsno, wname, title, content, passwd, wdate)
values((select nvl(max(bbsno),0)+1 as bbsno from bbs), '홍길동', '게시판제목',
'게시판내용', '1234', sysdate);

--read(select) 조회
select * from bbs
where bbsno = 1;

--전체 글 목록
select * from bbs
order by bbsno desc;

--조회수 증가
update bbs
set viewcnt = viewcnt + 1
where bbsno = 1;

--update(수정)
update bbs
set wname = '왕눈이',
    title = '제목 수정',
    content = '내용 수정'
where bbsno = 1;

--비번검증(cnt:1 인증 성공, cnt:0 인증 실패)
select count(bbsno) as cnt
from bbs
where bbsno = 1
and passwd = '1234';

--글 삭제
delete from bbs;

delete from bbs where bbsno = 1;

--grpno 증가하는 create
INSERT INTO bbs(bbsno, wname, title, content, passwd, wdate,grpno)  
VALUES((SELECT NVL(MAX(bbsno), 0) + 1 as bbsno FROM bbs), 
'왕눈이', '제목', '내용', '123', sysdate,
(SELECT NVL(MAX(grpno), 0) + 1  FROM bbs)
);

--정렬, 페이징
sELECT bbsno, wname, title, viewcnt, wdate, grpno, indent, ansnum, r
from(
    SELECT bbsno, wname, title, viewcnt, wdate, grpno, indent, ansnum, rownum r
    from(
    SELECT bbsno, wname, title, viewcnt, wdate, grpno, indent, ansnum
    FROM bbs
    --where wname like'%홍%' 여기서부터 아래로
    ORDER BY grpno DESC, ansnum ASC
        )
    )
where r >= 6 and r <= 10;


--7. 답변 
 
--1) 모든 레코드 삭제 
DELETE FROM bbs; 

--2) 부모글(일반적인 글) 등록 
 
INSERT INTO bbs(bbsno, wname, title, content, passwd, wdate, 
grpno)  
VALUES((SELECT NVL(MAX(bbsno), 0) + 1 as bbsno FROM bbs), 
'왕눈이', '부모글3', '내용', '123', sysdate,
(SELECT NVL(MAX(grpno), 0) + 1 as grpno FROM bbs)
);                 
 
         
SELECT bbsno, title, grpno, indent, ansnum 
FROM bbs  
ORDER BY grpno DESC, ansnum ASC; 
  
     
--3) 답변처리
-- read(부모의 title,grpno,indent,ansnum) : 첫번째
select bbsno, title, grpno, indent, ansnum
from bbs
where bbsno=2; --부모의 글

-- 답변등록(부모의같은 grpno, 부모+ 1 -> indent,ansnum): 세번째
  INSERT INTO bbs(bbsno, wname, title, content, passwd, wdate, 
  grpno,indent,ansnum)  
  VALUES((SELECT NVL(MAX(bbsno), 0) + 1 as bbsno FROM bbs), 
  '아로미', '부모글1 답변글2', '내용', '123', sysdate,2,1,1);
 
     
-- 부모글의 2차이상의 답변인 경우 ansnum(답변의 순서) 재정렬 :두번째
 UPDATE bbs 
 SET ansnum = ansnum + 1 
 WHERE grpno=2 AND ansnum > 0;



