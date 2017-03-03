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
        
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:0px;">
                <div class="alert alert-info">Item: ${item.getName()}</div>
            </div>
        </div>
        
        
        
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="min-height:50px;">
            Bid must be greater than <b>${bidValue}</b>. Grab it fast!
            <form method="post">
                <input type="hidden" name="itemId" value="${item.getId()}"/>
                Bid value:  <input type="text" name="price"/>
                <!--p><input type="submit"/></p-->
                <br/><br/>
                <button type="submit" class="btn btn-info btn-lg">Submit query</button>   
        
            </form>
        </div>
    </body>
</html>
