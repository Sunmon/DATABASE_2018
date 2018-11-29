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



  

### GUI와 Main 동기화 :: Sync

로그인 버튼을 눌러서 제대로 로그인 될 때까지 main의 다음 코드가 실행되지 않게 하고 싶을 때

sync이용!

```java
class Main
{
    public static void main()
    {
        GUIlogin gLogin = new GUIlogin();	//Login 관련 GUI
        User user = gLogin.login();			//login하는 메소드
        if(user != null) System.out.println("LOGINED!");
        else System.out.println("LOGIN FAILED");
    }
}
```

이렇게 코드를 짜면 gLogin.login() 메소드 실행이 끝날 때까지 7번째 라인은 실행 안 될 줄 알았다.

그리고 로그인이 끝나면 LOGINED라는 말이 나올 줄 알았는데...

main따로 GUI따로 돌아가서 gui에는 상관없이 바로 "LOGIN FAILED"라는 문구가 나왔다..



GUI에 있는 Login()메소드가 user를 제대로 리턴할 때 까지 main아래쪽에 있는 코드를 실행하지 않도록 하는 barrier가 필요했다. Sync를 맞추거나.

이 해결방법이 바로 `sync`다.



>  해결

gLogin을 바꿨다. button actionPerformed에 sync를 넣어준다.

```java
class GUIlogin
{
    public GUIlogin()
    {	//생성자
	    loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {  
                synchronized (loginButton)				 //loginButton에 대한 sync
                {
                    isValid(id,pw);						//id,pw가 맞는지 체크
                    loginButton.notify();				 //button이 눌렸다고 알림
                    
                }
            }
        });
    }
        
}
```



```java
class Main
{
    public static void main()
    {
        GUIlogin gLogin = new GUIlogin();	//Login 관련 GUI
		synchronized (glogin.loginButton)	//login button과 synchronize
        {
             try
            {   
                 vlogin.loginButton.wait();			//loginButton.notify()될때까지 기다림
       			User user = gLogin.login();			//login하는 메소드
  		    }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }  
        
        if(user != null) System.out.println("LOGINED!");
        else System.out.println("LOGIN FAILED");
    }
}

```



이렇게 하면 logined만 나온다.

7번째 줄이 실행되지 않고 기다리고 있다가, 로그인 되면 실행되기 때문이다.



\* 실제 코드 짠것의 전부를 가져오면 너무 방대해져서, 임의로 예시를 수정하여 작성했다. 





### GUI 다시 띄우기 :: revalidate(), repaint()

`revalidate()` : 재배치하기. 기존 component가 사라지면 할당한 공간자체를 없애버리고 모든 component들을 재 배치한다. component들 위치가 바뀐다.

`repaint()` : component 배치는 바꾸지 않고 그냥 생긴거만 새로고침한다. 있던게 사라져도 그냥 그 자리만 비게 보인다.





### ----------

sell_list에서 선택 -> 카트에 추가(카트에 없는것들만 추가해야겠지?)



insert into cart (p_code, customer_ID, seller_ID, p_count, tot_price)

select (s.p_code, p.customer_ID, s.seller_ID, 3, 3*s.price)

from (sell_list s, person p)

where 

select ()

from sell_list