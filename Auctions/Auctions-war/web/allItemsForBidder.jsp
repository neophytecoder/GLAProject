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
        <h1>Hello World!</h1>
        <form method="get" action="allItemsForBidder">
            <input type="text" id="username"  name="username"/>
            <input type="submit"/>
        </form>
        <table border="1">
            <c:if test="${items} not null">
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
                Highest Bid
            </td>
            <td>
                My Highest Bid
            </td>
            <td>Action</td>
            </tr>
            </c:if>
            
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
            <td>${row.getHighestBid()}</td>
            <td>${row.getMyHighestBid()}</td>
            <td><a href="cancelBid?username=${param.username}&bidId=${row.getId()}">Cancel</a></td>
            </tr>
        </c:forEach>
        </table>
        
    </body>
</html>
