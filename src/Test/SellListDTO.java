package Test;

public class SellListDTO implements DTO
{
    private String p_code = null;
    private String seller_ID = null;
    private int price = 0;
    private int stock = 0;
    private String size = null;
    private String p_nickname = null;


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








}
