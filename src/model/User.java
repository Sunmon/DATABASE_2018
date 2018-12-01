package model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class User
{
    //로그인한 유저 정보
    private String ID;
    private String pw;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private int height;
    private int weight;
    private int points;
    private String authority;

    DAOFactory df;


    //buyItem할 때 임시로 쓸 객체들.. 여기에 생성해서 이걸로 insert, delete등등 하면 된다.
    ArrayList<CartDTO> tempCart = null;
    ArrayList<SellListDTO> tempSell = null;
    ArrayList<FavoriteDTO> tempFavor = null;

    public User(String ID, String pw, String name, int age, String gender,
                String phone, String address, int height, int weight, int points, String authority)
    {
        this.ID = ID;
        this.pw = pw;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.points = points;
        this.authority = authority;
    }


    public void showLists(DAOFactory df) throws SQLException
    {   //list 보여줌.
        df.printAttributes();
        df.printAllItems();
    }

    //...List에서 삭제하기. DB까지 적용됨.
    public void remove(DAOFactory df, DTO dto)
    {
        df.delete(dto);
    }


    //...List에 추가하기. DB까지 적용됨.
    public void addList(DAOFactory df, DTO dto)
    {
        df.getDtoList().add(dto);
        df.insert(dto);
    }

    //메소드 오버로딩
    public void addList(ArrayList arr, DTO dto)
    {
        arr.add(dto);
    }

    public void buyItems(CartDAO cao, SellListDAO sao, Connector con)
    {
        for(int i=0; i<tempCart.size(); i++)
        {
            //재고가 충분한 경우
            int stock = tempSell.get(i).getStock();
            if(stock > tempCart.get(i).getP_count())
            {
                //아이템에 따른 point
                int totprice = tempCart.get(i).getTot_price();

                //Log에 추가
                CartDTO c = tempCart.get(i);
                insertLog(c.getP_code(), c.getSeller_ID(), totprice, con);

                //cartDB에서 삭제 / sellList에서 재고 감소
                cao.delete(c);
                tempSell.get(i).setStock(stock-tempCart.get(i).getP_count());
                sao.update(tempSell.get(i));


                //user point 감소
                setPoints(points-totprice);
                con.updateUserPoint(ID,-totprice);

                //seller point 증가
                con.updateUserPoint(tempSell.get(i).getSeller_ID(), totprice);


            }
        }
    }





    public void removeItems(CartDAO cao, Connector con)
    {   //tempCart에 있는것들 cart DB에서 삭제
        for(int i=0; i<tempCart.size(); i++)
        {
            cao.delete(tempCart.get(i));
        }
    }

    public void removeItems(favoriteDAO fao, Connector con)
    {
        for(int i=0; i<tempFavor.size(); i++)
        {
            fao.delete(tempFavor.get(i));
        }
    }


    public void initTempArrayList()
    {
        tempCart = new ArrayList<CartDTO>();
        tempSell = new ArrayList<SellListDTO>();
        tempFavor = new ArrayList<>();
    }

    public FavoriteDTO makeFavoriteDTO(String p_code, String seller_ID, String p_nickname, int price)
    {
        return new FavoriteDTO(p_code, this.ID, seller_ID, p_nickname, price);
    }



    public void insertLog(String p_code, String seller_ID, int point, Connector con)
    {


        con.insertLog(p_code, ID, seller_ID, point);

    }



    //sell_list에서 category별로 검색하기. ArrayList 반환.
    public ArrayList searchByCategory(SellListDAO sao, String cName)
    {
        ArrayList result = new ArrayList();
        for(SellListDTO sto : sao.getDtoList())
        {   //category이름이 입력과 일치하면 새 list에 추가.
            if(sto.getC_name().equals(cName)) result.add(sto);
        }
        return result;
    }


    //sell_list에서 pname으로 검색하기... new ArrayList 리턴
    public ArrayList searchByPname(SellListDAO sao, String pName)
    {
        ArrayList result = new ArrayList();
        for(SellListDTO sto : sao.getDtoList())
        {   //category이름이 입력과 일치하면 새 list에 추가.
            if(sto.getP_name().equals(pName)) result.add(sto);
        }
        return result;
    }



    //NOTE: cart관련 메소드들
    public CartDTO makeCartDTO(String pc, String sID, int pcount, int price, String nick)
    {   //CartDTO 객체 생성해서 리턴.
        return new CartDTO(pc, ID, sID, pcount, pcount*price, nick, price);
    }













    //Getter and Setter
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public DAOFactory getDf()
    {
        return df;
    }

    public void setDf(DAOFactory df)
    {
        this.df = df;
    }


    public ArrayList<CartDTO> getTempCart()
    {
        return tempCart;
    }

    public ArrayList<SellListDTO> getTempSell()
    {
        return tempSell;
    }


    public ArrayList<FavoriteDTO> gettempFavor()
    {
        return tempFavor;
    }
}
