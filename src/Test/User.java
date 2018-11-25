package Test;
import java.util.ListIterator;

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



    //TODO: cart, favorite, sell_list, 통합해서 보여주기 (귀찮아서 메소드 하나로 합침)
    public void showLists(DAOFactory df)
    {   //테스트중
        df.printAttributes();
        showCartList((CartDAO)df);

    }



    //NOTE: cart관련 메소드들

    public void removeCart(CartDAO cao, CartDTO cdo)
    {   //cartList에서 아이템 삭제 및 DB에서 삭제
        cao.deleteCartDB(cdo);
    }


    public void addCart(CartDAO cao, CartDTO cdo)
    {   //cartList에 새로운 아이템 추가 후 DB에도 추가
        cao.cartList.add(cdo);
        cao.insertCartDB(cdo);
    }

    public void showCartList(CartDAO cao)
    {   //cartList에 있는 내용들 print
        /*ListIterator<CartDTO> li= cao.cartList.listIterator();
        while(li.hasNext())
        {
            System.out.println();
        }*/

        //print cartList ... 고객에게 필요한 것 보여줌
        cao.printAttributes();
        for (CartDTO cto : cao.cartList)
        {
           printCartItem(cto);
        }
    }



    public CartDTO searchCart(CartDAO cao, String pc, String sID)
    {   //특정 DTO들을 검색해서 리턴.
        for (CartDTO cto : cao.cartList)
        {
            if(cto.getP_code().equals(pc)
                    && cto.getSeller_ID().equals(sID)) return cto;
        }

        System.out.println("찾는 상품이 장바구니에 없습니다.");
        return null;
    }


    public void printCartItem(CartDTO cto)
    {   //고른 상품을 print해주는 메소드
        //차후에 '카테고리별 보기' , '판매자별 보기' 등등에 사용
            System.out.print(cto.getP_nick() + "\t\t");
            System.out.print(cto.getP_count() + "\t\t\t");
            System.out.print(cto.getTot_price() + "\t\t\t");
            System.out.print(cto.getP_code()+ "\t\t\t");
            System.out.print(cto.getSeller_ID()+ "\t\t\t");
            System.out.println();
    }





    //GUI랑 쓰려면 sell_list에 연동해서 쓰든가 해야지
    /*public CartDTO makeCartDTO(Sell_listDTO st)
    {   //sellList에서 선택한 객체를 새 cartDTO로 리턴.
    }
     */



    public CartDTO makeCartDTO(String pc, String sID, int pcount, int price, String nick)
    {   //CartDTO 객체 생성해서 리턴.
        CartDTO ct = new CartDTO(pc, ID, sID, pcount, pcount*price, nick, price);
        return ct;
    }



    //NOTE: favorite 관련 메소드들



    //NOTE: sellList 관련 메소드들





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

}
