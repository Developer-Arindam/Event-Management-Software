# Event-Management-Software
An Event management software to manage University events and fests.This is a Java Swing Application. 
The UI Snapsots.pdf file uploaded here contains the UI images  , that gives a idea of the software.Code_folder has the java files of the application.
The software requires MYSQL database.


Guidelines to use the software

1)download the mysetup.exe file and install the application.
 the login page and account creation page will be functional.

2)database installation and setup guide for full functionality of the software
   if d=mysql database is preinstalled then just execute the following command to change the password
   
          >ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456789';
          
   now directly go to step 2)iii)

   if database is not previously installed then follow from below instruction.
   
   i)download mysql database. the link is given below
   https://dev.mysql.com/downloads/installer/

   download the 41MB version


   ii)install the mysql database
    the root password must have to be 123456789 . other wise the application cannot connect with the mysql database.


  iii)open the mysql command prompt.enter the password 123456789.
   
  then execute the following mysql commands
  
    >create database db;

    
    >use db;
    
    >create table account(name varchar(50),email varchar(50),phone varchar(10),password varchar(20),type varchar(12),college varchar(100));

    
    >create table event(name varchar(50),date varchar(50),venue varchar(100),time varchar(100),duration varchar(100),budget int(10),description varchar(300));

    
    >create table sponsor(ename varchar(50),sname varchar(50),mail varchar(100),amount varchar(20),status varchar(100));

    
    >create table registrar(ename varchar(50),date varchar(50),pname varchar(100),phno varchar(10),college varchar(100),mail varchar(100));


3)Set up is done. Open the application. First create an user account. After successful account creation click on 'back' then login.
