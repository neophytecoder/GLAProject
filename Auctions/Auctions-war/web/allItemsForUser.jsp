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
                <div class="alert alert-info">All items</div>
            </div>
        </div>
        
        <section class="menu-section">
        <div class="container">
            <div class="row ">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-left">
                            <c:forEach items="${categories}" var="row">
                                <a href="allItemsForUser?category=${row.getId()}">${row.getName()}</a>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
        </section> 
        
        <br/>
        
        <form method="get" action="allItemsForUser">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:50px;">Search by name
            <input type="text" name="name"/>
            <button type="submit" class="btn btn-info btn-lg" value="search">Search </button> 
            <!--input type="submit" value="search"/-->
            </div>
        </form>        
        
        <br/>
        
        <div class="container">          
            <table class="table table-bordered table-hover table-condensed" >
              <thead>
                <tr class="bg-info">
                    <th>Name</th>
                    <th>Description</th>
                    <th>Start price</th>
                    <th>Duration</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Categories</th>
                    <th>Highest Bid</th>
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
                    <td style="font-weight: bold">${row.getHighestBid()}</td>
                    <td>
                        <c:if test="${canBid}"><a href="bidItem?itemId=${row.getId()}">bid</a></c:if>
                    </td>
                    </tr>

                </c:forEach>
              </tbody>
              </table>
        </div>

    </body>
</html>
