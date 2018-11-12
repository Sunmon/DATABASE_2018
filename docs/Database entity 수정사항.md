# Database entity 수정사항 #1110

Entity 구조가 변경됨에 따라, 변경된 customer-seller-admin에 관계된 엔티티들에 많은 변화가 있음.



## 논리모델



### Entity 구조

0. administor -> admin으로 변경. 스펠링 틀려서.

1. person-admin / person-seller를 m:m관계에서 상속 관계로 변경

2. seller가 상속으로 변하게 되면서 기타 연결된 entity들에 변경사항이 생김.




### Entity 속성

####seller

> seller

`seller`가 `person`의 sub-entity로 변경됨에 따라,

shop_name을 primary key로 사용하지 못하게 됨. 대신 ID를 primary key로 사용.

income이라는 명칭을 revenue로 변경

| Seller:: 기존 | Seller:: 변경후  |
| ------------- | ---------------- |
| \#shop_name   | \#ID(FK)  (상속) |
| ID(FK)        | *shop_name       |
| password      | revenue          |
| name          |                  |
| phone         |                  |
| point         |                  |
| income        |                  |





>sell_list

`seller`가 변경됨에 따라 `sell_list`에도 변경이 있음

shop_name이 더 이상 primary key가 아니니, seller_ID를 primary key로 사용.





> Favorite

`seller`와 `sell_list`의 수정으로 인해, `favorite`도 수정.

기존의 `favorite` Entity에 대한 설명이 부족해 보여, attribute들을 추가.

고객이 상품코드만 보고 물품을 구분 할 수 없을 것 같아 p_name을 추가. 고객이 수정할 수 있다.

seller_ID는 sell_list의 primary key가 seller_ID로 변경됨에 따라, 자동으로 추가됨.



| Favorite:: 기존   | Favorite:: 변경후 |
| ----------------- | ----------------- |
| \#p_code(FK)      | \#p_code(FK)      |
| \#customer_ID(FK) | \#customer_ID(FK) |
|                   | p_name            |
|                   | *seller_ID(FK)    |



> log

`seller`가 변경됨에 따라, `log`에도 변경이 있음

ID라는 속성이 customer/ seller둘 다 있으니 명칭을 변경.

customer_ID(from cart)와 seller_ID(from seller)로



> cart

`seller`와  `sell_list`가 변경됨에 따라, `cart`도 변경

shop_name대신 seller_ID를 FK로 사용.

고객이 상품 코드와 seller ID만으로 카트에 뭘 담았는지 구분하기 어려울 것 같아, p_name을 추가. sell_list에 있던 p_name을 가져온다.



#### Category

> Category

c_code_sub를 추가함.

기존의 c_code는 대분류로 사용(상의/하의/신발/외투)

c_code_sub는 소분류로 사용(상의-니트,맨투맨, 터틀넥)



> Type

`Category`가 수정됨에 따라, `Type`도 수정사항이 생김

c_code_sub 추가.



---

## 물리모델



### View 생성

v_seller

v_customer

v_admin





###제약조건 설정