public class CustomerTest implements UserService{

    Connector con;
    String table;
    String attributes;  //metadata에서 읽어오든가 해야할듯

    public CustomerTest(Connector con)
    {
        this.con = con;
    }

    @Override
    public void buyItems() {
        //그냥 test용으로 잘 돌아가는지 만든거임.
        //사는 기능 없음!! 그냥 insert되는지 확인하려고

    }


    public void setTable(String table)
    {
        this.table = table;
    }

    public void insertData()
    {
        //TODO: 여기 하다 말음.
        String sql = "INSERT INTO" + table + "(";

    }
}
