package oderhandling;

import confighandling.Confighandler;
import dbhandling.SQLiteHandler;
import errorhandling.HandleError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christopher
 */
public class DeleteParts extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Confighandler config = new Confighandler(this.getServletContext().getResourceAsStream("/WEB-INF/config.txt"));
            SQLiteHandler handler = new SQLiteHandler();
            Connection c = handler.createConnection(Confighandler.getSavefile());
            Statement stmt = c.createStatement();
            Enumeration<String> walker = request.getParameterNames();

            while (walker.hasMoreElements()) {
                int currentID = Integer.parseInt(walker.nextElement());
                stmt.execute("delete from parts where id = " + currentID);
            }

            response.sendRedirect("deleteparts.jsp?status=deleteok");

        } catch (SQLException e) {
            new HandleError().handleError(e);
        } catch (IOException e) {
            new HandleError().handleError(e);

        }

    }
}

