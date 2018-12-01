
-- 1. create account	//java
INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight) 
values("sunmon", "1234", "sunjung", 23, "F", "010-4543-5364", "songpa-gu", 168, 0);


-- create customer's information view
CREATE OR REPLACE VIEW info_customer
AS (
	SELECT  ID, pw, name, age, gender, phone, address, height, points, weight, authority
	FROM person);


-- 1.1. create account by using 'info_customer' view
INSERT INTO info_customer
values("sunmon2", "1234", "sunjung", 23, "F", "010-1233-1233", "songpa-gu", 180, 100, 0, "customers");

-- 2. show customer's information	//java
select *
from info_customer;
# where id = "sunmon";


-- 3. update customer's information	//java
UPDATE info_customer
SET height = 160, points = 10
WHERE id = "sunmon2";


-- 4. add points to customer	// java
UPDATE info_customer
SET points = points + 100
#where id = "sunmon2";

-- 5. login	//java
SELECT id, pw
FROM person
WHERE id = "sunmon";


-- 6. show favorites	//java
SELECT *
FROM cart
WHERE customer_id = "sunmon";


-- 7. add favorites	//java
INSERT INTO favorites
values("AAdd-11", "sunmon2", "seller_1");


-- 7.1 add favorites	//java
INSERT INTO favorites
SELECT p_code, "sunmon", seller_id
FROM sell_list
WHERE p_nickname = "cute t-shirt"; 



-- 8. remove from favorites	//java
DELETE FROM favorites
where p_code = "AAab-12"
	AND id = "sunmon";


-- 9. show cart
SELECT *
FROM cart
WHERE id = "sunmon";


-- 10. add to cart	//확실하지 않음!
INSERT INTO cart
VALUES("AAbb-12", "sunmon", "seller1", "1", "0");

UPDATE cart
SET price = 
(
SELECT price * 
	( SELECT p_count
		FROM cart
		WHERE customer_ID = "sunmon" AND p_code = "AAbb-12" AND seller_ID = "seller1"
	)
FROM sell_list
WHERE p_code = "AAbb-12" AND seller_ID = "seller1";
);


-- 11. buy items from cart // by 세웅

-- 12.1. search by size
select *
from sell_list
where size = "customer_size";



-- 12.2 calculate customer's size
select (특수한 처리문 java로?) as customer_size 
from customer
where id = "sunmon";


-- 13. search by category //by 한나

-- 14. show customer's log
SELECT *
FROM sell_log
WHERE customer_ID = "sunmon";



-- 15. show logs
select s.p_nickname, l.*
from sell_list s, sell_log l
where customer_id = "sunmon" AND s.p_code = l.p_code;


-- 16. search using name
select s.p_nickname, l.*
from sell_list s, sell_log l
where s.p_name LIKE '%sun%' AND s.p_code = l.p_code
order by p_nickname;




select *
from favorites;

select *
from sell_list;


select *
from person, category, p_type, product, sell_log ;

select *
from category;


select *
from product;


select *
from p_type;



-- for testing category ... 13
INSERT INTO category
values ("AA", "bb", "tutle");

INSERT INTO category
values ("AA", "cc", "round");

INSERT INTO category
values ("BB", "aa", "jacket");

INSERT INTO category
values ("BB", "dd", "coat");


-- for testing product ...
INSERT INTO product
values ("KONKUK", "Gwa-Jam");

INSERT INTO product
values ("AAbb-12", "nice round tutleneck");

INSERT INTO product
values ("AAdd-11", "awesome shirt");


-- for testing p_type
INSERT INTO p_type
values("AAdd-11", "AA", "bb");

INSERT INTO p_type
values("KONKUK", "BB", "aa");

INSERT INTO p_type
values ("AAbb-12", "AA", "bb");

select *
from product;

select *

select *
from p_type;

-- for testing seller
-- create account
INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight) 
values("seller_1", "1234", "seller1", 23, "F", "010-1231-1111", "songpa-gu", 168, 100);

-- modify authority user to sellers
UPDATE person
SET authority = "sellers"
where id = "seller_1";


-- for testing sell_list
INSERT INTO sell_list
values("AAdd-11", "seller_1", 0, 1, "S", "cute t-shirt");


select *
from sell_list;
select *
from person;