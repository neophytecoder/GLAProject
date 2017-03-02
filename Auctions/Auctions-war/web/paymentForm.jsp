<%-- 
    Document   : paymentForm
    Created on : Mar 2, 2017, 6:41:03 PM
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


        <c:if test="${not empty param.pay and totalOrder!=0}">
            <fieldset>
                <p><b>Shipping cost</b> is ${shippingCost} </p>
                <p><b>Your total order</b> is ${totalOrder}</p>

                <form method="post" action="myShoppingCart">
                    <input type="hidden" name="totalOrder" value="${totalOrder}"/>
                    <p>Address: <input type="text" value="${address}" name="address"/></p>
                    <p>Bank Account: <input type="text" value="${bankAccount}"  name="bankAccount"/></p>
                    <p>Pin code: <input type="text" value="" name="pin"/></p>
                    <input type="submit" value="confirm order"/>

                </form>
            </fieldset>
        </c:if>   

    </body>
</html>
