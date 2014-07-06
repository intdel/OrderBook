package oderhandling;

import confighandling.Confighandler;
import dbhandling.SQLiteHandler;
import errorhandling.HandleError;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christopher
 */
public class Addorder extends HttpServlet {

    private StringBuffer savefile;
    private Connection connection;
    private Statement stmt;
    private final String orders_prequery = "insert into orders (name, qty, notes) values (";
    private final String parts_prequery = "insert into parts (name) values ('";

    //Reads working directory from servlet and config file
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletContext servletContext = this.getServletContext();
            InputStream in = servletContext.getResourceAsStream("/WEB-INF/config.txt");
            Confighandler confighandler = new Confighandler(in);
            this.savefile = Confighandler.getSavefile();
            connection = new SQLiteHandler().createConnection(savefile);
            stmt = connection.createStatement();
            handleData(stmt, request);
            stmt.close();
            connection.close();
            response.sendRedirect("addorder.jsp?status=done");
        } catch (SQLException e) {
            new HandleError().handleError(e);
        } catch (IOException e) {
            new HandleError().handleError(e);
        }

    }

    //Uses the parameter from previous jsp file to write it to a file in savedir
    public void handleData(Statement stmt, HttpServletRequest request) {

        try {
            Enumeration<String> walker = request.getParameterNames();
            boolean hasItem = false;
            String currentParameter;
            String currentValue;
            String sql = orders_prequery;
            while (walker.hasMoreElements()) {
                currentParameter = walker.nextElement();
                currentValue = request.getParameter(currentParameter);
                if (currentValue.length() > 0) {
                    if (currentParameter.contains("item") && currentValue.length() > 0) {
                        hasItem = true;
                        int count = stmt.executeQuery("select count(*) from parts where name='" + currentValue + "'").getInt(1);
                        if ( count <= 0) {
                            stmt.execute(parts_prequery + currentValue + "')"); //adds a new part to the parts table
                        }
                    }
                    if (hasItem) {
                        sql += "'" + currentValue + "'";

                    }
                    if (currentParameter.contains("notes") && hasItem) {
                        sql += ")";
                        hasItem = false;
                        stmt.executeUpdate(sql);
                        sql = orders_prequery;
                    } else {
                        sql += ", ";
                    }
                }
            }
        } catch (SQLException e) {
            new HandleError().handleError(e);
        }
    }
}
