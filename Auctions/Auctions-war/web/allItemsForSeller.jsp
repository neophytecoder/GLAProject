<%-- 
    Document   : allItemsForSeller
    Created on : Mar 1, 2017, 11:38:51 AM
    Author     : root
--%>

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
        <h1>My Selling Items</h1>
        
        <table border="1">
            <tr>
            <td>
                Name
            </td>
            <td>
                Description
            </td>
            <td>
                Start price
            </td>
            <td>
                Duration
            </td>
            <td>
                Start Date
            </td>
            <td>
                End Date
            </td>
            <td>
                Categories
            </td>
            <td>
                State
            </td>
            <td>
                Winner
            </td>
            <td>Action</td>
            </tr>
            
        <c:forEach items="${items}" var="row">
            <tr>
            <td>${row.getName()}</td>
            <td>${row.getDescription()}</td>
            <td>${row.getStartPrice()}</td>
            <td>${row.getDuration()}</td>
            <td>${row.getStartDate()}</td>
            <td>${row.getEndDate()}</td>
            <td>${row.getCategories()}</td>
            <td>${row.getState()}</td>
            <td>${row.getWinner().getName()}</td>
            <td><a href="cancelItem?username=${param.username}&itemId=${row.getId()}">Cancel</a></td>
            </tr>
        </c:forEach>
        </table>
        
    </body>
</html>
