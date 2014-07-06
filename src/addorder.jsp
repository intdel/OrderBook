<%-- 
    Document   : addorder
    Created on : Jun 26, 2014, 7:45:36 PM
    Author     : Christopher
--%>

<%@page import="oderhandling.GetParts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Order</title>
    </head>
    <body>
        <%  String inputs = "";
            String status = "";
            StringBuffer[][] names;
            GetParts parts = new GetParts();

            names = parts.readParts(this.getServletContext().getResourceAsStream("/WEB-INF/config.txt"));
            
            if (names.length <= 0)
            {
                response.sendRedirect("addnewparts.jsp?status=nofile");
            }

            int i = 0;
            for (i = 0; i < 5; i++) {
                inputs += "<tr><td><select name=\"item" + i + "\" form=\"orders\">";
                inputs += "<option name=\"item\" value=\"\"></option>";
                for (int j = 0; j < names.length; j++) {

                    inputs += "<option value=\"" + names[j][1].toString() + "\">" + names[j][1].toString() + "</option>";
                }
                inputs += "</select></td>";
                inputs += "<td><input type=\"text\" name=\"qty" + i + "\"></td><td><input type=\"text\" value=\" \" name=\"notes" + i + "\"></td></tr> ";
            }

            if (request.getParameter("status") != null && request.getParameter("status").equalsIgnoreCase("done")) {
                status = "<b>Order added succesfully!</b><br><br>";
            }

        %>
        <h1>Add a new order with existing part</h1>
        <%=status%>
        <div><form action="addorder" method="post" id="orders">
                <table border="1">
                    <tr><td>Item name</td><td>Qty</td><td>Notes</td></tr> 
                    <%=inputs%>
                </table>
                <input type="submit" value="Submit order">
            </form></div>
        <a href="addnewparts.jsp">Add new parts</a>
        <a href="vieworder.jsp">View existing orders</a>
    </body>
</html>
