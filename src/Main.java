//import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //testing for connection
        Connector con = new Connector("3306", "ku_db", "sunmon", "computer");
        con.insertData();

    }
}