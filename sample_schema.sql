## テーブルを作成
CREATE TABLE test (
	id INT PRIMARY KEY,
	name VARCHAR(50),
	age INT
);

## テストデータ
INSERT INTO test (id, name, age) VALUES (1, 'Alice', 25);
INSERT INTO test (id, name, age) VALUES (2, 'Bob', 30);
INSERT INTO test (id, name, age) VALUES (3, 'Charlie', 22);
INSERT INTO test (id, name, age) VALUES (4, 'David', 28);
INSERT INTO test (id, name, age) VALUES (5, 'Eve', 29);

## 一覧取得
select * from test;

DROP TABLE companies;

## companiesテーブル作成
CREATE TABLE companies (
	company_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	company_name VARCHAR(500),
	prtimes_url VARCHAR(2000),
	email VARCHAR(500),
	charge_employee VARCHAR(2000),
	category VARCHAR(500),
	number_of_employees INT,
	is_listed boolean,
	created_date datetime
)

INSERT INTO companies (company_id, company_name, prtimes_url, email, charge_employee, category, created_date) 
VALUES (1, '株式会社おいしい健康', 'https://prtimes.jp/main/html/rd/p/000000084.000043855.html', 'press@oishi-kenko.com', '', 'アプリ', '2023-06-25 12:16:40');




