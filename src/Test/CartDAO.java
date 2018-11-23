package Test;

import java.util.ArrayList;

public interface CartDAO {
    ArrayList<CartDTO> cart = null;

    //initialize cartDTO list
    public ArrayList<CartDTO> initialize(String _id);

    //insert
    //이름 바꿀 수 잇음. service에서 그냥 ? ? ? ? 해서 cart로 table이름 정할수도 있음.
    public void insertCartDB();

    //delete
    //이름 바꿀 수 있음
    public void deleteCartDB();

    //update
    //이름 바꿀 수 있음
    public void updateCartDB();


    //select
    //이름 바꿀 수 있음
    public void selectCartDB();


}
