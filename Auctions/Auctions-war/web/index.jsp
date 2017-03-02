<%-- 
    Document   : index
    Created on : Mar 2, 2017, 4:57:47 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>Я молодец :)</div>
        
        <% response.sendRedirect("login.jsp");  %>
    </body>
</html>
