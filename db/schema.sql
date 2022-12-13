-- DROP DATABASE IF EXISTS sns;
CREATE DATABASE IF NOT EXISTS sns;
USE sns;

CREATE TABLE users(
	u_index INT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(50),
    pw VARCHAR(30) NOT NULL,
    nickname VARCHAR(30) NOT NULL,
    comment VARCHAR(200),
	birth DATETIME,
    account_public CHAR(5), -- 공개 / 비공개 yes or no check조건 사용
    follow_approval CHAR(5), -- 팔로우 승인 / 비승인 yes or no check조건 사용
    picture VARCHAR(300)
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE boards(
	b_index INT AUTO_INCREMENT PRIMARY KEY,
	u_index INT NOT NULL,
    context VARCHAR(300) NOT NULL, # 게시물 내용
    file_type VARCHAR(30),
    multi_file VARCHAR(300),
	reg_date DATETIME DEFAULT NOW() # 등록 날짜
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE comments(
	com_index INT AUTO_INCREMENT PRIMARY KEY,
	b_index INT NOT NULL,
    u_index INT NOT NULL,
    depth INT,
    context VARCHAR(300) NOT NULL,
    reg_date DATETIME DEFAULT NOW()
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE curation(
	cu_index INT AUTO_INCREMENT PRIMARY KEY,
	u_index INT NOT NULL,
    set_name VARCHAR(30) NOT NULL,
    keyword VARCHAR(100) NOT NULL
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE followings(
	f_index INT AUTO_INCREMENT PRIMARY KEY,
	u_index INT NOT NULL,
    following_u_index INT NOT NULL,
	follow_approval CHAR(5) NOT NULL -- 팔로우 승인 / 비승인 yes or no check조건 사용
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE followers(
	f_index INT AUTO_INCREMENT PRIMARY KEY,
	u_index INT NOT NULL,
    follower_u_index INT NOT NULL,
    follow_approval CHAR(5) NOT NULL -- 팔로우 승인 / 비승인 yes or no check조건 사용
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE likes(
	l_index INT AUTO_INCREMENT PRIMARY KEY,
	b_index INT,
    like_u_index INT NOT NULL
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE notice(
	n_index INT AUTO_INCREMENT PRIMARY KEY,
	u_index INT,
    b_index INT,
	inv_index INT,
    act VARCHAR(30),
    seen CHAR(5) NOT NULL
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE scrape(
	s_index INT AUTO_INCREMENT PRIMARY KEY,
	b_index INT,
    u_index INT
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE hashtags(
	h_index INT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(50) UNIQUE
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE boardhashs(
	bh_index INT AUTO_INCREMENT PRIMARY KEY,
    b_index INT NOT NULL,
    h_index INT NOT NULL
)DEFAULT charset=utf8
COLLATE=utf8_bin;

CREATE TABLE data_file(
	d_index INT AUTO_INCREMENT PRIMARY KEY,
    data_file LONGBLOB,
    b_index INT NOT NULL,
    idx INT NOT NULL
)DEFAULT charset=utf8
COLLATE=utf8_bin;