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
        <!--form>
        <h1>Welcome, ${user.getUserName()}</h1>
        <h2>
            <a href="allItemsForUser">All items</a>
            <a href="allItemsForBidder">My bids</a>
            <a href="allItems">My selling items</a>
            <a href="createAuction">Create auction</a>
            <a href="myShoppingCart">My shopping cart</a>
            <a href="logout">Logout</a>
        </h2>
        <br/>
        </form>
        <br/-->
        <div class="below-slideshow">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                        <div class="txt-block">
                            <h1 class="head-line"> WELCOME, ${user.getUserName()}</h1>						
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="menu-section">
        <div class="container">
            <div class="row ">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a href="allItemsForUser" >ALL ITEMS</a></li>
                           
                            <li><a href="allItemsForBidder" >MY BIDS</a></li>
                            <li><a href="allItems" >MY SELLING ITEMS</a></li>
                            <li><a href="createAuction">CREATE AUCTION</a></li>
                            <li><a href="myShoppingCart">MY SHOPPING CART</a></li>
                            <li><a href="logout">LOGOUT</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
        
        
    </body>
</html>
