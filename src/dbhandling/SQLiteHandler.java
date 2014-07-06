package dbhandling;

import errorhandling.HandleError;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Christopher
 */
public class SQLiteHandler {

    //Creates an SQL connection
    public Connection createConnection(StringBuffer dataBaseLocation) {
        Connection c;
        File dbfile = new File(dataBaseLocation.toString());
        boolean newFile = true;
        Statement stmt;

        if (dbfile.exists()) {
            newFile = false;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dataBaseLocation.toString());

            if (newFile) {
                stmt = c.createStatement();
                stmt.execute("create table orders (id integer primary key autoincrement, name text, qty text, notes text)");
                stmt.execute("create table parts (id integer primary key autoincrement, name text)");
            }
            return c;
        } catch (SQLException e) {
            new HandleError().handleError(e);
            return null;
        } catch (ClassNotFoundException e) {
            new HandleError().handleError(e);
        }

        return null;
    }

}
