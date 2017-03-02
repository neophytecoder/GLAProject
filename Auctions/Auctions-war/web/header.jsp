<%-- 
    Document   : header
    Created on : Mar 1, 2017, 11:11:50 PM
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

        <h2>
        
        <a href="allItemsForUser">All items</a>
        <a href="allItemsForBidder">My bids</a>
        <a href="allItems">My selling items</a>
        <a href="createAuction">Create Auction</a>
        <a href="logout">logout</a>
        </h2>

        <h3>Hi User: ${user.getUserName()}</h3>
         <br/>
    </body>
</html>
