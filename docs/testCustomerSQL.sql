INSERT INTO person
values("sunmon", "1234", "sunjung", 23, "F", "010-4543-5364", "songpa-gu", 168, 0, 0, null, null, "customer");


-- create account
INSERT INTO person (ID, password, name, age, gender, phone, address, height, weight, authority) 
values("sunmon", "1234", "sunjung", 23, "F", "010-4543-5364", "songpa-gu", 168, 0, 0, null, null, "customer");



select stock
from sell_list
where seller_ID = "sunmon"
and p_code = "AA-123";