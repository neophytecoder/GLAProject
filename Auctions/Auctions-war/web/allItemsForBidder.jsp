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
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:0px;">
                <div class="alert alert-info">My bids</div>
            </div>
        </div>
        
        
        <div class="container">          
            <table class="table table-bordered table-hover table-condensed">
              <thead>
                <tr class="bg-info">
                  <th>Name</th>
                  <th>Description</th>
                  <th>Start price</th>
                  <th>Duration</th>
                  <th>Start Date</th>
                  <th>End Date</th>
                  <th>Categories</th>
                  <th>Status</th>
                  <th>Highest Bid</th>
                  <th>My Highest Bid</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody
            
                <c:forEach items="${items}" var="row">
                    <tr>
                        <td style="font-weight: bold">${row.getName()}</td>
                        <td>${row.getDescription()}</td>
                        <td style="font-weight: bold">${row.getStartPrice()}</td>
                        <td>${row.getDuration()} days</td>
                        <td>${row.getStartDate()}</td>
                        <td>${row.getEndDate()}</td>
                        <td>${row.getCategories()}</td>
                        <td><%@include file="statePrinter.jsp" %></td>
                        <td style="font-weight: bold">${row.getHighestBid()}</td>
                        <td style="font-weight: bold">${row.getMyHighestBid()}</td>
                        <td>
                        <a href="cancelBid?username=${param.username}&itemId=${row.getId()}">Quit Auction</a>
                        <c:if test="${row.getState() == 1 && row.getWinner().getId()==user.getId()}">
                             <a href="addToShoppingCart?itemId=${row.getId()}">Add to shopping cart</a>
                        </c:if>
                    </td>
                    </tr>
                </c:forEach>
              </tbody>
              </table>
        </div>
    </body>
</html>
