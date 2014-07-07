<%-- 
    Document   : vieworder
    Created on : Jun 27, 2014, 7:08:35 PM
    Author     : Christopher
--%>

<%@page import="oderhandling.GetOrders"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Orders</title>
    </head>
    <body>
        <h1></h1>
        <%
            String output = "";
            StringBuffer[][] data;
            GetOrders getOrders = new GetOrders();
            data = getOrders.readOrders(this.getServletContext().getResourceAsStream("/WEB-INF/config.txt"));

            if (data == null) {
                response.sendRedirect("addnewparts.jsp?status=nofile");
            } else {

                for (int i = 0; i < data.length; i++) {
                    if (data[i][0] != null) {
                        output += "<tr><td><input type=\"checkbox\" name=\"" + data[i][0].toString() + "\"></td>";
                        for (int j = 0; j < data[i].length; j++) {
                            output += "<td>" + data[i][j].toString() + "</td>";
                        }
                        output += "</tr>";
                    }
                }
            }

            if (output.equals("")) {
                output = "There are no database entries!";
            }
        %>
        <table border="1">
            <tr><td>Done</td><td>ID</td><td>Item name</td><td>Qty</td><td>Notes</td></tr>
            <%=output%>
        </table>
        <input type="button"
               onClick="window.print()"
               value="Print This Page"/>

    </body>
</html>
