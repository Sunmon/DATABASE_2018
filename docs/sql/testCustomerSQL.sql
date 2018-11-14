
-- create account
INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight, authority) 
values("sunmon", "1234", "sunjung", 23, "F", "010-4543-5364", "songpa-gu", 168, 0, "customer");


-- create customer's information view
CREATE VIEW info_customer
AS (
	SELECT  ID, pw, name, age, gender, phone, address, height, points, weight, authority
	FROM person
	WHERE ID = "sunmon" );


-- TODO: 여기 실험. 그전에 번호 안겹치게 하고, 기본 권한 customer로.	
-- create account by using 'info_customer' view
INSERT INTO info_customer
values("sunmon2", "1234", "sunjung", 23, "F", "010-1231-1231", "songpa-gu", 180, 100, "customer");

select *
from person;

select *
from info_customer;
