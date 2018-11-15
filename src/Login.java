import java.sql.SQLException;

public class Login {
    static String table = "person";

    public static Person getAuthority(String _id, String _pw, Connector con) throws SQLException {
        //TODO: db 접속해서 .
        String sql = "select id from person where id = "+_id;
        con.select(sql);

        String p = con.rs.getString("pw");


        return new Person(con);
    }



}
