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


//TODO: cart, favorite, sell_list, 통합해서 보여주기 (DAOFactory 이용)
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

















    /*//NOTE: sellList관련 메소드들
    private void showSellList(SellListDAO slao) throws SQLException
    {
        slao.printAttributes();
        for (SellListDTO sto : slao.sellList)
        {
            printSellListItem(sto);
        }
    }*/



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






    /*//NOTE: cart관련 메소드들

    public void removeCart(CartDAO cao, CartDTO cdo)
    {   //cartList에서 아이템 삭제 및 DB에서 삭제
        cao.deleteCartDB(cdo);
    }


    public void addCart(CartDAO cao, CartDTO cdo)
    {   //cartList에 새로운 아이템 추가 후 DB에도 추가
//        cao.cartList.add(cdo);
//        cao.insertCartDB(cdo);
        cao.dtoList.add(cdo);
        cao.insert(cdo);
    }*/

/*    public void showCartList(CartDAO cao) throws SQLException
    {   //cartList에 있는 내용들 print
        *//*ListIterator<CartDTO> li= cao.cartList.listIterator();
        while(li.hasNext())
        {
            System.out.println();
        }*//*

        //print cartList ... 고객에게 필요한 것 보여줌
        cao.printAttributes();
        for (CartDTO cto : cao.cartList)
        {
           printCartItem(cto);
        }
    }*/



/*
    public CartDTO searchCart(CartDAO cao, String pc, String sID)
    {   //특정 DTO들을 검색해서 리턴.

//        for (CartDTO cto : cao.cartList)
        for(CartDTO cto: cao.dtoList)
        {
            if(cto.getP_code().equals(pc)
                    && cto.getSeller_ID().equals(sID)) return cto;
        }

        System.out.println("찾는 상품이 장바구니에 없습니다.");
        return null;
    }
*/


    public CartDTO makeCartDTO(String pc, String sID, int pcount, int price, String nick)
    {   //CartDTO 객체 생성해서 리턴.
        return new CartDTO(pc, ID, sID, pcount, pcount*price, nick, price);
    }







    /*public void printCartItem(CartDTO cto)
    {   //고른 상품을 print해주는 메소드
        //차후에 '카테고리별 보기' , '판매자별 보기' 등등에 사용
            System.out.print(cto.getP_nick() + "\t\t");
            System.out.print(cto.getP_count() + "\t\t\t");
            System.out.print(cto.getTot_price() + "\t\t\t");
            System.out.print(cto.getP_code()+ "\t\t\t");
            System.out.print(cto.getSeller_ID()+ "\t\t\t");
            System.out.println();
    }*/

 /*   public void printSellListItem(SellListDTO sto)
    {   //고른 상품을 print해주는 메소드
        //차후에 '카테고리별 보기' , '판매자별 보기' 등등에 사용

        System.out.print(sto.getP_nickname() + "\t\t");
        System.out.print(sto.getSize() + "\t\t\t");
        System.out.print(sto.getPrice() + "\t\t\t");
        System.out.print(sto.getStock()+ "\t\t\t");
        System.out.print(sto.getSeller_ID()+ "\t\t\t");
        System.out.print(sto.getP_code()+ "\t\t\t");
        System.out.println();
    }*/




    //GUI랑 쓰려면 sell_list에 연동해서 쓰든가 해야지
    /*public CartDTO makeCartDTO(Sell_listDTO st)
    {   //sellList에서 선택한 객체를 새 cartDTO로 리턴.
    }
     */












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
