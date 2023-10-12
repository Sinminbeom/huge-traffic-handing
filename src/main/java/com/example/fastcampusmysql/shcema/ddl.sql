create table Member
(
    id int auto_increment,
    email varchar(20) not null,
    nickname varchar(20) not null,
    birthday date not null,
    createdAt datetime not null,
    constraint member_id_uindex
        primary key (id)
);

create table MemberNicknameHistory
(
    id int auto_increment,
    memberId int not null,
    nickname varchar(20) not null,
    createdAt datetime not null,
    constraint memberNicknameHistory_id_uindex
        primary key (id)
);

create table Follow
(
    id int auto_increment,
    fromMemberId int not null,
    toMemberId int not null,
    createdAt datetime not null,
    constraint Follow_id_uindex
        primary key (id)
);

create unique index Follow_fromMemberId_toMemberId_uindex
    on Follow (fromMemberId, toMemberId);

create table POST
(
    id int auto_increment,
    memberId int not null,
    contents varchar(100) not null,
    createdDate date not null,
    createdAt datetime not null,
    constraint POST_id_uindex
        primary key (id)
);

create index POST__index_member_id
    on POST (memberId);

create index POST__index_created_date
    on POST (createdDate);

create index POST__index_member_id_created_date
    on POST (memberId, createdDate);

create table Timeline
(
    id int auto_increment,
    memberId int not null,
    postId int not null,
    createdAt datetime not null,
    constraint Timeline_id_uindex primary key (id)
);

#####################################################
### 쿼리 테스트
#####################################################
SELECT createdDate, memberId, count(*) as count
FROM POST
WHERE memberId = 1
  AND createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

select memberId, count(id)
from POST
group by memberId;

select createdDate, count(id)
from POST
group by createdDate
order by 2 desc;

select count(distinct createdDate)
from POST;

explain SELECT createdDate, memberId, count(*) as count
        FROM POST use index (POST__index_member_id)
        WHERE memberId = 3
          AND createdDate between '1900-01-01' and '2023-01-01'
        GROUP BY memberId, createdDate;

explain SELECT createdDate, memberId, count(*) as count
        FROM POST use index (POST__index_created_date)
        WHERE memberId = 3
          AND createdDate between '1900-01-01' and '2023-01-01'
        GROUP BY memberId, createdDate; # 17s

SELECT *
FROM POST use index (POST__index_created_date); # 52ms

SELECT createdDate, memberId, count(*) as count
FROM POST use index (POST__index_created_date)
WHERE memberId = 4
  AND createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate; # 17s

# 첫번째 데이터 분포
# 두번째 데이터 검색의 범위
# 셋번째 where 절에 인덱스가 걸려있지 않는 컬럼이 있는지

#####################################################

alter table POST add column likeCount int;
alter table POST add column version int default 0;


select * from POST where createdAt = '2016-03-27 00:35:00';

# 테이블 전체 락걸림
start transaction;
select * from POST where createdAt = '2016-03-27 00:35:00' for update;
commit;

# memberId가 4인 데이터들 모두 락걸림
# lock은 해당 row에 lock이 걸리는게 아니고 인덱스에 걸리기 때문에 다른 컬럼을 where문에 걸어도 인덱스 컬럼 모든 데이터에 락이 걸림
start transaction;
select * from POST where memberId = 4 and contents = 'timeline push test' for update;
commit;

# memberId가 4인 데이터들 모두 락걸림
start transaction;
select * from POST where memberId = 4 and contents = 'string' for update;
commit;

select count(*) from performance_schema.data_locks where LOCK_TYPE = 'RECORD';
select * from information_schema.INNODB_TRX;

#####################################################

create table PostLike
(
    id int auto_increment,
    memberId int not null,
    postId int not null,
    createdAt datetime not null,
    constraint PostLike_id_uindex primary key (id)
)
