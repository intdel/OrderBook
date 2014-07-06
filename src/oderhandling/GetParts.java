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
public class GetParts {

    public StringBuffer savefile;
    public StringBuffer[][] tempString;
    private final String parts_query = "select id, name from parts order by name";

    //Reads from file and creates a StringBuffer Array
    public StringBuffer[][] readParts(InputStream in) {
        try {
            new confighandling.Confighandler(in);
            savefile = confighandling.Confighandler.getSavefile();
            Connection c = new SQLiteHandler().createConnection(savefile);
            Statement stmt = c.createStatement();
            int i = 0;
            ResultSet rs = stmt.executeQuery(parts_query);
            while (rs.next()) {
                i++;
            }
            tempString = new StringBuffer[i][2];
            rs = stmt.executeQuery(parts_query);
            rs.next();
            for (i = 0; i < tempString.length; i++) {
                tempString[i][0] = new StringBuffer(rs.getString("id"));
                tempString[i][1] = new StringBuffer(rs.getString("name"));
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
