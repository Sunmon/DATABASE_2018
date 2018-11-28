package model;

public class SellListDTO implements DTO
{
    private String p_code = null;
    private String seller_ID = null;
    private int price = 0;
    private int stock = 0;
    private String size = null;
    private String p_nickname = null;

    //sell_list에 추가적으로 가질 정보들 (고객에겐 안 보이더라도. 검색을 용이하게 하기 위해):: p_name, c_name, c_code, c_code_sub
    private String p_name = null;                   //from product
    private String c_code = null;                   //from category
    private String c_code_sub = null;               //from category
    private String c_name = null;                   //from category


    public SellListDTO(String p_code, String seller_ID, int price, int stock, String size, String p_nickname, String p_name, String c_code, String c_code_sub, String c_name)
    {
        this.p_code = p_code;
        this.seller_ID = seller_ID;
        this.price = price;
        this.stock = stock;
        this.size = size;
        this.p_nickname = p_nickname;
        this.p_name = p_name;
        this.c_code = c_code;
        this.c_code_sub = c_code_sub;
        this.c_name = c_name;
    }


    public SellListDTO(String p_code, String seller_ID, int price, int stock, String size, String p_nickname)
    {
        this.p_code = p_code;
        this.seller_ID = seller_ID;
        this.price = price;
        this.stock = stock;
        this.size = size;
        this.p_nickname = p_nickname;
    }

    public String getP_code()
    {
        return p_code;
    }

    public void setP_code(String p_code)
    {
        this.p_code = p_code;
    }

    public String getSeller_ID()
    {
        return seller_ID;
    }

    public void setSeller_ID(String seller_ID)
    {
        this.seller_ID = seller_ID;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getStock()
    {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getP_nickname()
    {
        return p_nickname;
    }

    public void setP_nickname(String p_nickname)
    {
        this.p_nickname = p_nickname;
    }

    public String getC_code()
    {
        return c_code;
    }

    public void setC_code(String c_code)
    {
        this.c_code = c_code;
    }

    public String getC_code_sub()
    {
        return c_code_sub;
    }

    public void setC_code_sub(String c_code_sub)
    {
        this.c_code_sub = c_code_sub;
    }

    public String getC_name()
    {
        return c_name;
    }

    public void setC_name(String c_name)
    {
        this.c_name = c_name;
    }

    public String getP_name()
    {
        return p_name;
    }

    public void setP_name(String p_name)
    {
        this.p_name = p_name;
    }
}
