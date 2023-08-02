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