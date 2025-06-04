This Repository holds the BE implementation of the Flavor Flow application using Springboot framework. I have used JPA Hibernate ORM for the database connectivity.

Steps to Run BE server:

----------- Setting up Database -------------------
1) Install and Start MySQL server, keep a note of username and password used. refer: https://dev.mysql.com/doc/mysql-getting-started/en/
2) Using MySQL client or any other DB client connect to the MySQL server using your username and password.
3) Create a database with name 'falvordb'.

----------- Starting BE server ------------------
1) Clone this repository.
2) Open the project in IDE of youe choice, ie IntelliJ IDE
3) Open src\main\resources\application.properties and update the username and password for the running MySQL server.
4) Resolve all the dependencies, refer: https://intellij-support.jetbrains.com/hc/en-us/community/posts/21354698847250-How-to-perform-reload-all-Maven-projects-non-incrementally-with-the-classic-UI
5) Open the file src\main\java\com\example\FlavorFlow\FlavorFlowApplication.java and Run the application.

The shows the DB Schema: ![image](https://github.com/user-attachments/assets/67b42339-51cd-4efb-88ad-8bd2898d5efb)

