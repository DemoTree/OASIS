DROP DATABASE IF EXISTS oasis;
CREATE DATABASE oasis DEFAULT CHARACTER SET utf8;
USE oasis;

##创建用户表
##作者
CREATE TABLE t_author (
                          doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                          doc_title VARCHAR(200) unique not null ,

                          authors  VARCHAR(400) not null,
                          authors_aff VARCHAR(4000),
                          author_key  VARCHAR(700)
)ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
##发表
CREATE TABLE t_pub (
                       doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                       doc_title VARCHAR(200),

                       pub_title VARCHAR(200),
                       pub_year INT,
                       publisher VARCHAR(200),
                       doc_ident VARCHAR(50)
)ENGINE=InnoDB;
##论文
CREATE TABLE t_paper (
                         doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                         doc_title VARCHAR(200),

                         paper_vol VARCHAR(10),
                         paper_issue VARCHAR(10),
                         start_page INT,
                         end_page INT,
                         abstract VARCHAR(5000)
)ENGINE=InnoDB;
##期刊
CREATE TABLE t_per (
                       doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                       doc_title VARCHAR(200),

                       per_ISSN VARCHAR(10),
                       per_ISBNs VARCHAR(10),
                       license  VARCHAR(10)
)ENGINE=InnoDB;
##检索
CREATE TABLE t_search (
                          doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                          doc_title VARCHAR(200),

                          sea_date VARCHAR(10),
                          sea_dol VARCHAR(50),
                          sea_fund VARCHAR(10),
                          sea_pdf VARCHAR(150),
                          sea_INSP VARCHAR(1000),
                          sea_INSPNON VARCHAR(1000)
)ENGINE=InnoDB;
##术语
CREATE TABLE t_term (
                        doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                        doc_title VARCHAR(500),

                        mesh_terms VARCHAR(500),
                        IEEE_terms VARCHAR(500)
)ENGINE=InnoDB;
##引用计数
CREATE TABLE `t_count` (
                           `doc_id` int(11) NOT NULL AUTO_INCREMENT,
                           `doc_title` varchar(200) unique NOT NULL DEFAULT '',
                           `count_art` int(11) NOT NULL DEFAULT '0',
                           `count_ref` int(11) NOT NULL DEFAULT '0',
                           PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1290 DEFAULT CHARSET=utf8;
##数据
CREATE TABLE t_data (
                        doc_id   INT AUTO_INCREMENT PRIMARY KEY,
                        doc_title VARCHAR(200),

                        data_onl  VARCHAR(100),
                        data_iss  VARCHAR(100),
                        data_mee VARCHAR(100)
)ENGINE=InnoDB;

##多对多关系
###文章--作者+国籍
CREATE TABLE d_author(
    cid INT AUTO_INCREMENT PRIMARY KEY ,
    doc_title VARCHAR(200) not null,

    author VARCHAR(200) not null,
    author_aff VARCHAR(200),
    author_place INT
)ENGINE =InnoDB;

###文章--研究关键字
CREATE TABLE d_key(
    cid        INT AUTO_INCREMENT PRIMARY KEY,
    doc_title  VARCHAR(200),

    author_key  VARCHAR(700)
)ENGINE =InnoDB;

###文章--IEEE_TERMS
CREATE TABLE d_term(
    cid        INT AUTO_INCREMENT PRIMARY KEY,
    doc_title  VARCHAR(200),

    IEEE_term VARCHAR(500)
)ENGINE =InnoDB;

###文章--INSP
CREATE TABLE d_INSP(
    cid        INT AUTO_INCREMENT PRIMARY KEY,
    doc_title  VARCHAR(200),

    sea_INSP VARCHAR(1000)
)ENGINE =InnoDB;

###文章--INSPNON
CREATE TABLE d_INSPEC(
    cid        INT AUTO_INCREMENT PRIMARY KEY,
    doc_title  VARCHAR(200),

    sea_INSPNON VARCHAR(1000)
)ENGINE =InnoDB;

##存储活跃度
###作者活跃度
CREATE TABLE `h_author` (
    `author` varchar(200) NOT NULL,
    `aff` varchar(200) NOT NULL,
    `heat` double DEFAULT 0,
     PRIMARY KEY (`author`,`aff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


###机构活跃度
CREATE TABLE h_aff(
    aff varchar(200),
    heat double,
    primary key(aff)
)ENGINE =InnoDB;

###authorKey活跃度
CREATE TABLE h_key(
    keyword varchar(200),
    heat double,
    primary key (keyword)
)ENGINE =InnoDB;

CREATE TABLE author_rank (
    author VARCHAR(32) NOT NULL DEFAULT 'NAA',
    pub_count INT NOT NULL DEFAULT 0,
    cite_count INT NOT NULL DEFAULT 0,
    PRIMARY KEY (author),
    INDEX idx_pub(pub_count ASC),
    INDEX idx_cite (cite_count ASC));

ALTER TABLE `oasis`.`d_author`
    ADD INDEX `idx_author` (`author` ASC);
;


COMMIT;