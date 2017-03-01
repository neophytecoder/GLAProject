<%-- 
    Document   : login
    Created on : Mar 1, 2017, 9:52:44 PM
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
        Login:
        <form method="post" action="login">
            Username: <input type="text" id="username"  name="username"/>
            Password: <input type="password" id="password"  name="password"/>
            <input type="submit" value="login"/>
        </form>
    </body>
</html>
