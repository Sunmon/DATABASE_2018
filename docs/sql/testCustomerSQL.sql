
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
#WHERE id = "sunmon";


-- 6. show favorites	//java
SELECT *
FROM cart
#WHERE customer_id = "sunmon";


-- 7. add favorites	//java
INSERT INTO favorites
values("AA-121", "sunmon2", "eiwh");




select *
from person, category, type, product, log ;

select *
from category;

INSERT INTO category
values ("AA", "bb", 
