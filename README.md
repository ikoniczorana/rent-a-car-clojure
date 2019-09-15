# Clojure app for rent a car agency

The project was developed as part of the assignment for the course Software Engineering Tools and Methodology on Master's studies - Software Engineering and Computer Sciences at the Faculty of Organization Sciences, University of Belgrade, Serbia. 
The main goal of the application design is to help the renting agencies employees record the requirements for renting the vehicle in the system. 

## Prerequisites
You will need Leiningen 2.0 or above and MySQL installed.

## Installing
First step is create database rentacar

CREATE DATABASE rentacar DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

You need to change database parameters that is based in files in configuration folder: 
>db-config.edn & migratus.conf.edn

After changing parameters, navigate to your project directory and execute the following command in the command line for database migrations:
```
lein migratus migrate
```
## Running
To start a web server for the application, run:

    lein ring server 

