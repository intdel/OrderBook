package oderhandling;

import dbhandling.SQLiteHandler;
import errorhandling.HandleError;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Christopher
 */
public class GetOrders {

    public StringBuffer savefile;
    public StringBuffer[][] tempString;
    private final String query = "select id, name, qty, notes from orders";
    private final int column_amt = 4;

    //Reads from file and creates a StringBuffer Array
    public StringBuffer[][] readOrders(InputStream in) {
        try {
            new confighandling.Confighandler(in);
            savefile = confighandling.Confighandler.getSavefile();
            Connection c = new SQLiteHandler().createConnection(savefile);
            Statement stmt = c.createStatement();
            int i = 0;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                i++;
            }
            tempString = new StringBuffer[i][column_amt];
            rs = stmt.executeQuery(query);
            rs.next();
            for (i = 0; i < tempString.length; i++) {
                tempString[i][0] = new StringBuffer(rs.getString("id"));
                tempString[i][1] = new StringBuffer(rs.getString("name"));
                tempString[i][2] = new StringBuffer(rs.getString("qty"));
                tempString[i][3] = new StringBuffer(rs.getString("notes"));

                rs.next();
            }
            stmt.close();
            c.close();
            return tempString;

        } catch (SQLException e) {
            new HandleError().handleError(e);
            return null;
        }
    }

}
