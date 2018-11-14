
-- create account
INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight, authority) 
values("sunmon", "1234", "sunjung", 23, "F", "010-4543-5364", "songpa-gu", 168, 0, "customer");

-- create customer's information view
CREATE VIEW info_customer
AS ( select  ID, pw, name, age, gender, phone, address, height, points, weight


select *
from person;
