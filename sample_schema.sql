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

## companiesテーブル作成
CREATE TABLE companies (
	id INT PRIMARY KEY,
	company_name VARCHAR(50),
	prtimes_url VARCHAR(200),
	email VARCHAR(50),
	charge_employee VARCHAR(200),
	category VARCHAR(50),
	created_date datetime
)

INSERT INTO companies (company_name, prtimes_url, email, charge_employee, category, created_date) 
VALUES ("株式会社おいしい健康", "https://prtimes.jp/main/html/rd/p/000000084.000043855.html", "press@oishi-kenko.com", "", "アプリ", "2023-06-25 12:16:40");


