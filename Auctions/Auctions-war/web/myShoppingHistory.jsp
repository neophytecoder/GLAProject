<%-- 
    Document   : myShoppingHistory
    Created on : Mar 2, 2017, 8:26:11 PM
    Author     : Juan Karsten
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br/><br/><br/>
        <h1>My transaction histories: </h1>
        <table border="1">
            <tr>
                <td>Items</td>
                <td>Bank Account</td>
                <td>Transaction Date</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${histories}" var="row">
                <tr>
                <td>${row.getItems()}</td>
                <td>${row.getBankAccount()}</td>
                <td>${row.getTransactionDate()}</td>
                <td>${row.getResult()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
