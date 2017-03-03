<%-- 
    Document   : createAuction
    Created on : Mar 2, 2017, 4:36:30 PM
    Author     : root
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
        <%@include file="header.jsp" %>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:0px;">
                <div class="alert alert-info">Create auction</div>
            </div>
        </div>
        
        
        
        <form method="post" action="createAuction">
            <div class="container-fluid">
                <div class="row">
                  <div class="col-sm-1">Name</div>
                  <div class="col-sm-1"><input type="text" name="name"/></div>
                </div>
                <div class="row">
                  <div class="col-sm-1">Description</div>
                  <div class="col-sm-1"><textarea type="text" name="description"></textarea></div>
                </div>
                <div class="row">
                  <div class="col-sm-1">Start Price</div>
                  <div class="col-sm-1"><input type="text" name="startPrice"/></div>
                </div>
                <div class="row">
                  <div class="col-sm-1">Duration</div>
                  <div class="col-sm-1"><input type="text" name="duration"/></div>
                </div>
                <div class="row">
                  <div class="col-sm-1">Categories</div>
                  <div class="col-sm-8">
                    <c:forEach var="row" items="${categories}">
                        <input type="checkbox" name="category" value="${row.id}"/>${row.name}
                    </c:forEach></div>
                </div>
                <br/>
            
            <button type="submit" class="btn btn-info btn-lg" value="create auction">Create auction </button>   
        </form>
        </div>
    </body>
</html>
