CREATE TABLE `cart`
(
    `customer_ID`    VARCHAR(20) NOT NULL
 COMMENT 'customer_ID',
    `p_code`    VARCHAR(20) NOT NULL
 COMMENT 'p_code',
    `count`    DECIMAL
 COMMENT 'count',
    `tot_price`    DECIMAL
 COMMENT 'tot_price',
    `seller_ID`    VARCHAR(20) NOT NULL
 COMMENT 'seller_ID',
    `p_name`    VARCHAR(20)
 COMMENT 'p_name'
) INSERT_METHOD = FIRST
 COMMENT = 'cart';

ALTER TABLE `cart`
 ADD CONSTRAINT `cart_PK` PRIMARY KEY ( `customer_ID`,`p_code` );


CREATE TABLE `category`
(
    `c_code`    VARCHAR(20) NOT NULL
 COMMENT 'c_code',
    `c_name`    VARCHAR(20)
 COMMENT 'c_name',
    `c_code_sub`    VARCHAR(20)
 COMMENT 'c_code_sub'
) INSERT_METHOD = FIRST
 COMMENT = 'category';

ALTER TABLE `category`
 ADD CONSTRAINT `엔터티7_PK` PRIMARY KEY ( `c_code` );


CREATE TABLE `favorites`
(
    `p_code`    VARCHAR(20) NOT NULL
 COMMENT 'p_code',
    `customer_ID`    VARCHAR(20) NOT NULL
 COMMENT 'customer_ID',
    `p_name`    VARCHAR(20)
 COMMENT 'p_name',
    `seller_ID`    VARCHAR(20) NOT NULL
 COMMENT 'seller_ID'
) INSERT_METHOD = FIRST
 COMMENT = 'favorites';

ALTER TABLE `favorites`
 ADD CONSTRAINT `favorites_PK` PRIMARY KEY ( `p_code`,`customer_ID` );


CREATE TABLE `log`
(
    `customer_ID`    VARCHAR(20) NOT NULL
 COMMENT 'customer_ID',
    `p_code`    VARCHAR(20) NOT NULL
 COMMENT 'p_code',
    `point`    DECIMAL
 COMMENT 'point',
    `pay_time`    DATETIME
 COMMENT 'pay_time',
    `seller_ID`    VARCHAR(20) NOT NULL
 COMMENT 'seller_ID'
) INSERT_METHOD = FIRST
 COMMENT = 'log';

ALTER TABLE `log`
 ADD CONSTRAINT `log_PK` PRIMARY KEY ( `customer_ID`,`p_code`,`seller_ID` );


CREATE TABLE `login`
(
    `ID`    VARCHAR(20) NOT NULL
 COMMENT 'ID',
    `password`    VARCHAR(20)
 COMMENT 'password',
    `authority`    VARCHAR(20)
 COMMENT 'authority'
) INSERT_METHOD = FIRST
 COMMENT = 'login';

ALTER TABLE `login`
 ADD CONSTRAINT `엔터티4_PK` PRIMARY KEY ( `ID` );


CREATE TABLE `person`
(
    `ID`    VARCHAR(20) NOT NULL
 COMMENT 'ID',
    `password`    VARCHAR(20) NOT NULL
 COMMENT 'password',
    `name`    VARCHAR(20) NOT NULL
 COMMENT 'name',
    `age`    DECIMAL NOT NULL
 COMMENT 'age',
    `gender`    CHAR(1) NOT NULL
 COMMENT 'gender',
    `phone`    VARCHAR(20) NOT NULL
 COMMENT 'phone',
    `address`    VARCHAR(100) NOT NULL
 COMMENT 'address',
    `height`    DECIMAL
 COMMENT 'height',
    `weight`    DECIMAL
 COMMENT 'weight',
    `point`    DECIMAL
 COMMENT 'point',
    `shop_name`    VARCHAR(20)
 COMMENT 'shop_name',
    `revenue`    DECIMAL
 COMMENT 'revenue'
) INSERT_METHOD = FIRST
 COMMENT = 'person';

ALTER TABLE `person`
 ADD CONSTRAINT `엔터티1_PK` PRIMARY KEY ( `ID` );


CREATE TABLE `product`
(
    `p_code`    VARCHAR(20) NOT NULL
 COMMENT 'p_code'
) INSERT_METHOD = FIRST
 COMMENT = 'product';

ALTER TABLE `product`
 ADD CONSTRAINT `엔터티1_PK4` PRIMARY KEY ( `p_code` );


CREATE TABLE `sell_list`
(
    `p_code`    VARCHAR(20) NOT NULL
 COMMENT 'p_code',
    `p_name`    VARCHAR(20)
 COMMENT 'p_name',
    `price`    DECIMAL
 COMMENT 'price',
    `stock`    DECIMAL
 COMMENT 'stock',
    `size`    VARCHAR(2)
 COMMENT 'size',
    `seller_ID`    VARCHAR(20) NOT NULL
 COMMENT 'seller_ID'
) INSERT_METHOD = FIRST
 COMMENT = 'sell_list';

ALTER TABLE `sell_list`
 ADD CONSTRAINT `sell_list_PK` PRIMARY KEY ( `p_code`,`seller_ID` );


CREATE TABLE `type`
(
    `p_code`    VARCHAR(20) NOT NULL
 COMMENT 'p_code',
    `c_code`    VARCHAR(20) NOT NULL
 COMMENT 'c_code',
    `c_code_sub`    VARCHAR(20)
 COMMENT 'c_code_sub'
) INSERT_METHOD = FIRST
 COMMENT = 'type';

ALTER TABLE `type`
 ADD CONSTRAINT `Relation1_PK` PRIMARY KEY ( `p_code` );