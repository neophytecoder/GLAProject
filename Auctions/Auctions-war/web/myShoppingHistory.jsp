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
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:0px;">
                <div class="alert alert-info">My transactions history</div>
            </div>
        </div>
        
        <div class="container">          
            <table class="table table-bordered table-hover table-condensed">
              <thead>
                <tr class="bg-info">
                  <th>Items</th>
                  <th>Bank Account</th>
                  <th>Transaction Date</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${histories}" var="row">
                    <tr>
                    <td style="font-weight: bold">${row.getItems()}</td>
                    <td>${row.getBankAccount()}</td>
                    <td>${row.getTransactionDate()}</td>
                    <td style="font-weight: bold">${row.getResult()}</td>
                    </tr>
                </c:forEach>
              </tbody>
              </table>
        </div>
    </body>
</html>
