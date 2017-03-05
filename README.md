# GLAProject
PROJECT Auctions
by Akulina lyapunova, Jordina Frances de Mas, Juan Karsten, Nikita Vasilenko

The web application is developed for auction management. The application allows customers to sell items and to bid for buying them.

Getting Started
--------------------------
These instructions explain how to deploy the project on a local machine for development and testing purposes.

Prerequisites
* NetBeans 8.2
* Java EE 7
* GlassFish Server 4
* Derby server integrated with NetBeans

Resources, needed for the application
JDBC (standard resources, built in GlassFish server)
--------------------------
1) Database sample (username and password is app)
2) JDBC Connection Pool: SamplePool
3) JDBC Resource: jdbc/sample

JMS Destination Resources (must be created manually)
--------------------------
1) ReservationQueue 
- JNDI Name, Physical Destination name: ReservationQueue
- Resource Type: javax.jms.Queue
2) ResultsQueue
- JNDI NAme, Physical Destination name: ResultsQueue
- Resource Type: javax.jms.Queue

Before first deployment of the application, we recommend to delete all tables from "sample" database. 


How to use the application
--------------------------
After the application is launched, you will meet login page (login.jsp). Only authorized users can use the application. 
There are 10 pre-registered users, with following user names and passwords:
* Username: user1 (password: user1), user2 (user2), user3(user3) and so on up to user10(user10).

After the user is authorized, he can use all the features of the application:
* Submit items for sell (link CREATE AUCTION, page createAuction)
* Bid on items (link ALL ITEMS, page allItemsForUser)
* Order items the user won (link MY BIDS, page allItemsForBidder)
* Pay for the items he ordered: confirm the payment with and PIN code and wait for the payment confirmation (link MY SHOPPING CART, page myShoppingCart)

Remarks:
--------------------------
1) The "correct" PIN code is "1234", with it the payment will be confirmed. With any other PIN code the payment will be failed.
2) If a buyer withdraws his bid after the deadline more than 5 times, he won't be able to bid for 7 days.

