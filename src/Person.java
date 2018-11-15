

public class Person
{
    Connector con;


    //NOTE: 기본정보들 enum으로 바꿀까 생각중
    String ID;
    String pw;
    String name;
    int age;
    String gender;
    String phone;
    String address;
    int height;
    int weight;
    int points;
    String authority;


    public Person(Connector con)
    {
        this.con = con;
    }

}
