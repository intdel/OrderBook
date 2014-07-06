<%-- 
    Document   : view_exist_parts.jsp
    Created on : Jun 30, 2014, 10:29:55 AM
    Author     : Christopher
--%>

<%@page import="oderhandling.GetParts"%>
<%@page import="dbhandling.SQLiteHandler"%>
<%@page import="java.sql.Connection"%>
<%@page import="confighandling.Confighandler"%>
<%@page import="java.io.InputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String output = "";
            StringBuffer[] result;
            GetParts parts = new GetParts();

            result = parts.readParts(this.getServletContext().getResourceAsStream("/WEB-INF/config.txt"));

            for (int i = 0; i < result.length; i++) {
                output += "<option>" + result[i] + "</option>\n";
            }


        %>
        <select>
            <%=output%>
        </select>
    </body>
</html>
