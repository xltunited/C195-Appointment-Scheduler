Tile and purpose of the application:
This application is an Appointment Scheduler, the purpose of it is to create appointments between "Customers" and "Contacts" during a
company's business hours.
Author: Eddy Bryan Leon Silva
Contact Information : eddy.b.leon@gmail.com
Application Version: Application Scheduler 1.0.0
Date: 03/06/2022
IDE Version Number =IntelliJ IDEA Community Edition 2021.3.2
JDK Version: Java 17.0.2
JavaFX: JavaFX-SDK-17.0.2
MySQL connector driver version: mysql-connector-java-8.0.28

How to run the program:
This program connects to a local instance of the user's database, you can log in with 2 users 1)Username:test/Password:test or 2)Username:admin/Password:admin.
This will present you with the main application screen. Here on the left-hand side you will see a table for all the customers in the database, and on the right
a table for all appointments in the database. You can add, update and delete customers. You can add, update and delete appointments. A rule for appointments
is that they must be scheduled during business hours which are 8AM - 10 PM EST. There are 3 report buttons. The appointments report counts how many appointments
happen in a given month, by type or by month and type. Contacts report makes a schedule for contacts listing all the appointments tied to them.

Additional Report: I made a schedule for customers and all of their related(Past or upcoming) appointments.

Lambda expressions are in methods
1) activateAutoSubmit in main/controller/AddAppointment.
2) deleteCustomer in main/controller/Application