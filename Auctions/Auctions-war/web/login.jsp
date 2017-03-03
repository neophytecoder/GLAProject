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
        <!-- BOOTSTRAP CORE STYLE  -->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONT AWESOME STYLE  -->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- ANIMATE STYLE  -->
        <link href="assets/css/animate.css" rel="stylesheet" />
        <!-- FLEXSLIDER STYLE  -->
        <link href="assets/css/flexslider.css" rel="stylesheet" />
        <!-- CUSTOM STYLE  -->
        <link href="assets/css/style.css" rel="stylesheet" />
        <!-- GOOGLE FONTS  -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css' />
         <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css' />
    </head>
    <body>
        <!--Login:
        <form method="post" action="login">
            Username: <input type="text" id="username"  name="username"/>
            Password: <input type="password" id="password"  name="password"/>
            <input type="submit" value="login"/>
        </form-->

        <form method="post" action="login">
        <div class="container ">
            <div class="row">
            <br/>
            <div class="col-lg-5 col-md-12 col-sm-2 col-xs-12 col-lg-offset-4 col-md-offset-1 col-sm-offset-1 col-xs-12 set-div">
                <div class="just-txt-div text-center">
                    <h3><strong>Login</strong> </h3>
                    <p>
                        Username: <input type="text" id="username"  name="username"/>
                        <br /><br />
                        Password: <input type="password" id="password"  name="password"/>
                    <br /><br />
                    </p>
                      <button type="submit" class="btn btn-info btn-lg" value="login">Sign in </button>
                </div>
               
                </div>
            </div>
         </div>
        </form>
    </body>
</html>
