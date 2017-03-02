<%-- 
    Document   : bidItem
    Created on : Mar 1, 2017, 1:44:20 PM
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
        <%@include file="header.jsp" %>
        
        <h1>Item: ${item.getName()}</h1>
        Bid must be greater than ${bidValue}. Grab it fast!
        
        <form method="post">
            <input type="hidden" name="itemId" value="${item.getId()}"/>
            Bid value:  <input type="text" name="price"/>
            <p><input type="submit"/></p>
        </form>
    </body>
</html>
