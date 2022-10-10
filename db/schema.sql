DROP DATABASE IF EXISTS sns;
create database sns;

use sns;

CREATE TABLE users(
	u_index int auto_increment primary key,
	email varchar(50),
    pw varchar(30) not null,
    nickname varchar(30) not null,
    comment varchar(200),
	birth datetime,
    account_public char(5), # 공개 / 비공개 yes or no check조건 사용
    follow_approval char(5), # 팔로우 승인 / 비승인 yes or no check조건 사용
    picture varchar(300)
)default charset=utf8
collate=utf8_bin;

CREATE TABLE boards(
	b_index int auto_increment primary key,
	u_index int not null,
    context varchar(300) not null, # 게시물 내용
    file_type varchar(30),
    multi_file varchar(300),
	reg_date datetime default now() # 등록 날짜
)default charset=utf8
collate=utf8_bin;

CREATE TABLE comments(
	com_index int auto_increment primary key,
	b_index int not null,
    u_index int not null,
    depth int,
    context varchar(300) not null,
    reg_date datetime default now()
)default charset=utf8
collate=utf8_bin;

CREATE TABLE curation(
	cu_index int auto_increment primary key,
	u_index int not null,
    set_name varchar(30) not null,
    keyword varchar(100) not null
)default charset=utf8
collate=utf8_bin;

CREATE TABLE followings(
	f_index int auto_increment primary key,
	u_index int not null,
    following_u_index int not null,
	follow_approval char(5) not null # 팔로우 승인 / 비승인 yes or no check조건 사용
)default charset=utf8
collate=utf8_bin;

CREATE TABLE followers(
	f_index int auto_increment primary key,
	u_index int not null,
    follower_u_index int not null,
    follow_approval char(5) not null # 팔로우 승인 / 비승인 yes or no check조건 사용
)default charset=utf8
collate=utf8_bin;

CREATE TABLE likes(
	l_index int auto_increment primary key,
	b_index int,
    like_u_index int not null
)default charset=utf8
collate=utf8_bin;

CREATE TABLE notice(
	n_index int auto_increment primary key,
	u_index int,
    b_index int,
	inv_index int,
    act varchar(30),
    seen char(5) not null
)default charset=utf8
collate=utf8_bin;

CREATE TABLE scrape(
	s_index int auto_increment primary key,
	b_index int,
    u_index int
)default charset=utf8
collate=utf8_bin;

CREATE TABLE hashtags(
	h_index INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(50) UNIQUE
)default charset=utf8
collate=utf8_bin;

CREATE TABLE boardhashs(
	bh_index INT AUTO_INCREMENT PRIMARY KEY,
    b_index INT NOT NULL,
    h_index INT NOT NULL
)default charset=utf8
collate=utf8_bin;

CREATE TABLE data_file(
	d_index INT AUTO_INCREMENT PRIMARY KEY,
    data_file longblob,
    b_index INT NOT NULL,
    idx INT NOT NULL
)default charset=utf8
collate=utf8_bin;