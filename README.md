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


## Libraries
Project was developed using:
- Leiningen: Leiningen is a build automation and dependency management tool for the simple configuration of software projects written in the Clojure programming language
- Ring:  It's a lower-level framework to handle HTTP requests, with a focus on traditional web development. 
- Migratus: A general migration framework, with implementations for migrations as SQL scripts or general Clojure code.
- Compojure: Compojure is a small routing library for Ring that allows web applications to be composed of small, independent parts.
- Korma: Korma is a domain specific language for Clojure. It is used for creating queries and communicating with db.
- Liberator: Liberator is used together with a e compojure to generate the resources’ representations.
- Struct: A structural validation library that is used to validate object before saving in db.
- Selmer: Selmer is a pure Clojure template system. It is used to render html files and pass data through routes.
- Buddy auth: It is used to provide Authentication and Authorization facilities.

## Project description

Project has different functionalities based on role that is assigned to user.

If the user with 'user' role is logged in, only pages for manipulating with rent requests are visible.
User mode consists of *Home page*, *Add request* page, and *All requests* page. 
At home page user can see ads for car sales, with photos and prices for cars.
At add request page, user can add new request for rent a car. 
At all request page, user can see all requests that is already submitted. As well, user can edit request. 
When the user wants to exit the system, there is an option in the menu bar for logout.
If the user with 'admin' role is logged in, the pages for manipulating with system users are also visible.
Admin can add new user into the system at the *Add user* page. 
Admin can see all users in the system at the *All users* page. As well, admin can edit password for user and change the user role. Additionaly, admin can delete users from the system.  


## Built with
- IntelliJ Idea


## References
Clojure for the brave and true
https://www.baeldung.com/clojure-ring
