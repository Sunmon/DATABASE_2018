-- person insert data

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
values("sunmon", "1234", "sunjung", 23, "F", "01045435364", "songpa-gu", 168, 50, 500000, "customers");

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
Values("han","123040","hanna", 22, "F", "01040392393", "konkuk", 169,53,20000,"customers");

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
Values("yih123","159483","ilhwan", 27, "M", "01043391253", "kangjin", 176,68,30000,"customers");

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
Values("jjj232","12223","janjin", 30, "M", "010233312133", "US", 180,73,1000,"customers");

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
Values("ddadandu","1244","chanju", 22, "F", "010403923193", "konkuk", 169,53,20000,"customers");

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
Values("jimm","12231","jimin", 23, "F", "01040097788", "seoul", 130,40,100000,"customers");

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority) 
Values("xoxo","133329","kei", 27, "M", "01012394794", "busan", 183,70,1000,"customers");


-- seller insert data
INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority,shop_name, revenue) 
Values("DBseller","12039","hana", 24, "F", "01049392393", "konkuk", 169,53,20000,"sellers","DB_shop",NULL);

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority,shop_name, revenue)
values("cmseller","134522","chunchun",44,"M","02394852029","kungki",173,60,100000,"sellers","Cm_shop",NULL);

INSERT INTO person (ID, pw, name, age, gender, phone, address, height, weight,points,authority,shop_name, revenue)
values("lalaseller","14403","sami",30,"F","01029395004","jeju",160, 32, 15000,"sellers","lala_shop",NULL);
