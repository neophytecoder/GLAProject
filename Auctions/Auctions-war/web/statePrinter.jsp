<%-- 
    Document   : statePrinter
    Created on : Mar 2, 2017, 9:18:55 PM
    Author     : Juan Karsten
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${row.getState()==1}">For sale</c:if>
<c:if test="${row.getState()==2}">Ordered</c:if>
<c:if test="${row.getState()==3}">Delivering</c:if>