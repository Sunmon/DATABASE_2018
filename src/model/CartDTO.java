package model;

public class CartDTO implements  DTO{
    //cart Table뿐 아니라, 고객이 cart 사용시 필요한 정보들을 다 담는 클래스
    //cart table + product table

    private String p_code = null;
    private String customer_ID = null;
    private String seller_ID = null;
    private int p_count = 0;
    private int tot_price = 0;
    private String p_nick = null;   //product nickname
    private int p_price = 0;        //1개당 가격


    //constructor
    public CartDTO(){}
    public CartDTO(String p_code, String customer_ID, String seller_ID, int p_count, int tot_price, String p_nick, int p_price) {
        this.p_code = p_code;
        this.customer_ID = customer_ID;
        this.seller_ID = seller_ID;
        this.p_count = p_count;
        this.tot_price = tot_price;
        this.p_nick = p_nick;
        this.p_price = p_price;
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

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getSeller_ID() {
        return seller_ID;
    }

    public void setSeller_ID(String seller_ID) {
        this.seller_ID = seller_ID;
    }

    public int getP_count() {
        return p_count;
    }

    public void setP_count(int p_count) {
        this.p_count = p_count;
    }

    public int getTot_price() {
        return tot_price;
    }

    public void setTot_price(int tot_price) {
        this.tot_price = tot_price;
    }

    public String getP_nick() {
        return p_nick;
    }

    public void setP_nick(String p_nick) {
        this.p_nick = p_nick;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }




}
