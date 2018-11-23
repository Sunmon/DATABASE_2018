# Tips

이번 DB프로젝트를 만들면서 도움되거나, 필요한 개념 정리



###DAO, DTO, VO

Database에 연결한 정보를 어딘가 저장해야 한다 -> 정보 저장 객체 : DTO, VO

VO 는 DAO에서 Setter뺀것 (read only)

실제 로직은 DAO에서.



http://allg.tistory.com/25



### Connection Methods

http://mozi.tistory.com/26

#### 1. executeUpdate()

select빼고 나머지에서 사용.

return값: int

insert/delete/update에서는 반영된 레코드 건수 리턴,

create/ drop에서는 -1 리턴.



###2. execute()

다 사용 가능

return값: boolean

return할때 resultSet을 반환하면(select 결과) true, 아니면 false 리턴(insert같은거).



### 3. executeQuery()

select에서 사용

return 값: resultSet



  

