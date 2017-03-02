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
        
        <h1>Create Auction</h1>

        <form method="post" action="createAuction">
            <p>Name: <input type="text" name="name"/></p>
            <p>Description: <textarea type="text" name="description"></textarea></p>
            <p>Start Price: <input type="text" name="startPrice"/></p>
            <p>Duration: <input type="text" name="duration"/></p>

            <p>
                <c:forEach var="row" items="${categories}">
                    <input type="checkbox" name="category" value="${row.id}"/>${row.name} 
                </c:forEach>
            </p>
            <p><input type="submit" value="create auction"/></p>
            
    </form>

</body>
</html>
