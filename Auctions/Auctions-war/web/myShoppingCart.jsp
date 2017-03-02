<%-- 
    Document   : allItemsForSeller
    Created on : Mar 1, 2017, 11:38:51 AM
    Author     : root
--%>

<%@page import="javax.ejb.EJB"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>My Shopping Cart</h1>
        
        
        <table border="1">
            <tr>
                <td>
                    Name
                </td>
                <td>
                    Description
                </td>
                <td>
                    Categories
                </td>
                <td>
                    Price
                </td>
                <td>
                    Action
                </td>
            </tr>
            
        <c:forEach items="${items}" var="row">
            <tr>
            <td>${row.getName()}</td>
            <td>${row.getDescription()}</td>
            <td>${row.getCategories()}</td>
            <td>${row.getHighestBid()}</td>
            <td>
                <a href="cancelShoppingCart?itemId=${row.getId()}">Cancel item</a>
            </td>
            </tr>
            
        </c:forEach>
        </table>
        
    </body>
</html>
