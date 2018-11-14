2018-11-14 `product` 테이블 컬럼 속성 변경:: 	p_name을 primary-> 보조식별자(#)로 변경..unique key

2018-11-14 `customer`테이블 컬럼명 변경 ::​		password -> pw / point -> points로 이름 변경.

2018-11-14 `customer`테이블 컬럼 속성 변경::	phone을 보조식별자로 설정(#).. unique key

​			`category`테이블 컬럼 속성 변경::	c_code_sub도 primary key로 설정

​			c_code만 primary로 설정하니까 중복된다고 값을 삽입할 수 없었음. (A ad 터틀넥 / A bc 가디건 삽입 불가)

​			이제 c_code_sub도 primary key로 설정했기 때문에 위와 같은 예시 삽입 가능