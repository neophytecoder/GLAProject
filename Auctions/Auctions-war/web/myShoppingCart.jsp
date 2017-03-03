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
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:0px;">
                <div class="alert alert-info">My shopping cart</div>
            </div>
        </div>
        
        <div class="container">          
            <table class="table table-bordered table-hover table-condensed">
              <thead>
                <tr class="bg-info">
                  <th>Name</th>
                  <th>Description</th>
                  <th>Categories</th>
                  <th>Price</th>
                  <th>Shipping cost</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody
            
                <c:forEach items="${items}" var="row">
                    <tr>
                    <td style="font-weight: bold">${row.getName()}</td>
                    <td>${row.getDescription()}</td>
                    <td>${row.getCategories()}</td>
                    <td style="font-weight: bold">${row.getHighestBid()}</td>
                    <td>
                        <c:if test="${not row.getFreeDelivery()}">
                            5 
                        </c:if>
                        <c:if test="${row.getFreeDelivery()}">
                            free
                        </c:if>
                    </td>
                    <td>
                        <a href="cancelShoppingCart?itemId=${row.getId()}">Cancel item</a>
                    </td>
                    </tr>

                </c:forEach>
              </tbody>
              </table>
        </div>

        <br/>
        
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:0px;">
            <a type="button" href="myShoppingCart?pay=true">Order items</a>
            <!--button type="submit" class="btn btn-info btn-lg" href="myShoppingCart?pay=true">Order items </button-->   
        </div>
        
        
       
         <%@include file="paymentForm.jsp" %>
         <%@include file="myShoppingHistory.jsp" %>
    </body>
</html>
