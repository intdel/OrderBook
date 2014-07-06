<%-- 
    Document   : addorder
    Created on : Jun 26, 2014, 7:45:36 PM
    Author     : Christopher
--%>

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
            
            int i = 0;
            for (i = 0; i < 5; i++) {
                inputs += "<tr><td><input type=\"text\" name=\"item" + i + "\"></td><td><input type=\"text\" name=\"qty" + i + "\"></td><td><input type=\"text\" value=\" \" name=\"notes" + i + "\"></td></tr> ";
            }
            
            if (request.getParameter("status") != null && request.getParameter("status").equalsIgnoreCase("done"))
            {
                status = "<b>Order added succesfully!</b><br><br>";
            }
            if (request.getParameter("status") != null && request.getParameter("status").equalsIgnoreCase("nofile"))
            {
                status = "<b>File does not exist! Please add something first.</b><br><br>";
            }

        %>
        <h1>Add a new order with a new part</h1>
        <%=status%>
        <div><form action="addorder" method="post">
                <table border="1">
                    <tr><td>Item name</td><td>Qty</td><td>Notes</td></tr> 
                    <%=inputs%>
                </table>
                <input type="submit" value="Submit order">
            </form></div>
                
    </body>
</html>
