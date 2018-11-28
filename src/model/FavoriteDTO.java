package model;

public class FavoriteDTO implements  DTO {

    private String p_code = null;
    private String customer_ID = null;
    private String seller_ID = null;
    private String p_nick = null;
    private int price = 0;

    public FavoriteDTO(){}
    public FavoriteDTO(String p_code, String customer_ID, String seller_ID, String p_nick, int price) {
        this.p_code = p_code;
        this.customer_ID = customer_ID;
        this.seller_ID = seller_ID;
        this.p_nick = p_nick;
        this.price = price;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public String getSeller_ID() {
        return seller_ID;
    }

    public void setSeller_ID(String seller_ID) {
        this.seller_ID = seller_ID;
    }

    public String getP_nick() {
        return p_nick;
    }

    public void setP_nick(String p_nick) {
        this.p_nick = p_nick;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }


}
