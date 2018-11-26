package Test;

public class Seller extends User
{   //Seller 클래스
    public String shop_name;
    private int revenue;

    public Seller(String ID, String pw, String name, int age, String gender, String phone, String address, int height, int weight, int points, String authority, String shop_name, int revenue)
    {
        super(ID, pw, name, age, gender, phone, address, height, weight, points, authority);
        this.shop_name = shop_name;
        this.revenue = revenue;
    }


    //Getter and Setter
    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }


}
