package test;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    //Login과 관련된 기능.
    static Connector conr;
    static String _id;
    static String _pw;
    static String _autho;

    public static Person Login(Connector _conr) throws SQLException {
        Scanner scan = new Scanner(System.in);
        conr = _conr;
        do
        {   //insert id & password
            System.out.println("input id:");
            _id = scan.next();
            System.out.println("input pw:");
            _pw = scan.next();
        }while(!isValid());
        return setUser();
    }
    public static Person setUser()
    {   //determinant user's class .. Person or Seller or Admin
        if(_autho.equals("customers")) return new Person();
        else if(_autho.equals("sellers")) return new Seller();
        else return new Admin();
    }


    //TODO: 주석달고 true return
    public static boolean isValid() {
        String sql = "select pw from person where id = " + _id;
        try {
            conr.pstmt = conr.con.prepareStatement(sql);
            conr.rs = conr.pstmt.executeQuery();

            if(!conr.rs.getString("pw").equals(_pw))
            {
                System.out.println("Wrong password!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Wrong id!");
            e.printStackTrace();
            return false;
        }


    }

}
