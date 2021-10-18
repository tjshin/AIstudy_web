CREATE TABLE memo ( 
  memono    NUMBER(7)       NOT NULL,   -- 글 일련 번호, -9999999 ~ +9999999 
  wname   VARCHAR(20)    NOT NULL,   -- 글쓴이 
  title        VARCHAR(100)  NOT NULL,   -- 제목(*) 
  content   VARCHAR(4000) NOT NULL,  -- 글 내용 
  passwd   VARCHAR(15)     NOT NULL,  -- 비밀 번호 
  viewcnt   NUMBER(5)       DEFAULT 0,  -- 조회수, 기본값 사용 
  wdate     DATE               NOT NULL,  -- 등록 날짜, sysdate 
  PRIMARY KEY (memono)  
); 

--c
insert into memo(memono, wname, title, content, passwd, wdate)
values((select nvl(max(memono), 0) + 1 from memo),
'홍길동', '오늘의 일정', '2시 점심약속', '1234', sysdate);

--r
select * from memo
where memono = 1;

--u
update memo
set title = '오늘의 일정 취소',
    content = '2시 점심 약속을 7시 저녁 약속으로 변경'
where memono = 1;

--d
delete memo
where memono = 1;

--password check
select count(memono) as cnt
from memo
where memono = 1 and passwd = '1234';

--increase viewcnt
update memo
set viewcnt = viewcnt +1
where memono = 1;

--list
select wname, title, viewcnt, wdate, r
from(
    select wname, title, viewcnt, wdate, rownum r
    from(
        select wname, title, viewcnt, wdate
        from memo
        --where wname like '%김%'//wname, title, content, title_content
        order by memono desc
    )
)
where r >= 1 and r <= 5;

--reset
delete memo;
