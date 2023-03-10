--===================
-- mybatis  -- 怨꾩젙�� web 
--=====================

create table student(
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date, -- 理쒓렐�닔�젙�씪
    deleted_at date,   -- �궘�젣�씪
    constraint pk_student_no primary key(no)
);
create SEQUENCE seq_student_no;

insert into student(no,name,tel) values( seq_student_no.NEXTVAL, '�떊�궗�엫�떦', '01051512121');
insert into student(no,name,tel) values( seq_student_no.NEXTVAL, '�씠�닚�떊', '01077778888');
insert into student(no,name,tel) values( seq_student_no.NEXTVAL, '�솉湲몃룞', '01011112222');


commit;
select * from student;


-- �닔�젙 
update student 
set tel = '01033334444', updated_at = sysdate 
where no = 3;

--�궘�젣 -- �솢 delete媛� �븘�땲�깘 ? �궘�젣�븳 �궇吏쒕�� �븣怨좎떢�뼱 
update student 
set  deleted_at = sysdate 
where no = 3;

commit;

select * from student  where deleted_at is null and  tel   like   '%77%';

-----
select * from student;


----------------------------- s h 계정의  employ / department / job /  sel_grade 


--- web에서 sh계정 테이블을 조회하고 싶다면, sh 혹은 관리자가 특정 테이블을 볼수있도록 권한을 줘야된다
grant all on sh.employee to web;
grant all on sh.department to web;
grant all on sh.job to web;
grant all on sh.sal_grade to web;

------ 테이블 조회 
select * from  sh.employee;
select * from  sh.department;
select * from  sh.job;
select * from  sh.sal_grade;



------ 동의어 객체 지정  =별칭 
-- 관리자가 web한테 권한을 부여해야한다. -----
grant create SYNONYM to web;
------------------------------------------------------
create synonym emp for sh.employee;
create synonym dept for sh.department;
create synonym job for sh.job;
create synonym sal_grade for sh.sal_grade;

--
select * from  emp;
select * from  dept;
select * from  job;
select * from  sal_grade;

--- 검색어 여러개일때 - 사번/사원명/이메일/전화번호검색 && 성별 검색 

select * 
from (  select e.*, decode(substr(emp_no,8,1), '1', '남', '3', '남', '여') gender 
               from emp e )
where quit_yn = 'N' and emp_name like '%이%'  and gender = '남';


select *
from emp
where hire_date  >= to_date('1990-01-01') -- 이날짜보다 큰날짜만나옴 



---- 다시 쿼리 내힘으로 만들어보기 -  phone에 25 들어가고, email에 s 들어가는  남자 구하기 
select *
from (
select e.*, decode( substr(emp_no,8,1),'1','남','3','남','여')gender
from emp e )
where email like '%or%' and gender = '남';


                                                                             