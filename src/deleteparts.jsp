<%-- 
    Document   : deleteparts.jsp
    Created on : Jun 30, 2014, 11:52:39 AM
    Author     : Christopher
--%>

<%@page import="oderhandling.GetParts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Delete parts</h1>

        <%
            String output = "";
            String status = "";
            if (request.getParameter("status") != null && request.getParameter("status").contains("deleteok"))
            {
                status = "Delete successful!<br><br>";
            }
            StringBuffer[][] data;
            GetParts parts = new GetParts();
            data = parts.readParts(this.getServletContext().getResourceAsStream("/WEB-INF/config.txt"));

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
            
            if (output.equals(""))
            {
                output = "There are no database entries.";
            }
        %>
        <%=status%>
        <table border="1">
            <form action="deleteparts" method="post">
                <%=output%>

        </table>
        <input type="submit" value="Delete">
</html>
