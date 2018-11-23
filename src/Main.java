//import java.sql.*;
import Test.*;

public class Main {
    public static void main(String[] args) {
        //testing for connection
//        Connector con = new Connector("3306", "ku_db", "sunmon", "computer");
//        con.insertData();
//
        //Test package에 있는거 새로 테스트하는거.
        Test.Connector con = new Test.Connector("3306", "dbtest_1115", "sunmon", "computer");
        con.initialize("sunmon");
//        con.insertCartDB("A-123", "sunmon", "seller_2", 1, 1000);
//        con.deleteCartDB("A-123", "sunmon", "seller_2");
    }
}