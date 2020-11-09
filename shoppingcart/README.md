# Java User Model Final Version / Exceptions Final Application

## Introduction

This is a basic database scheme with users, user emails, and user roles. This Java Spring REST API application will provide endpoints for clients to read various data sets contained in the application's data. This application will also form the basis of a user authentication application developed elsewhere in the course

### Database layout

The table layout is similar to the initial version with the following exceptions:

* The join table userroles is explicitly created. This allows us to add additional columns to the join table
* Since we are creating the join table ourselves, the Many to Many relationship that formed the join table is now two Many to One relationships
* All tables now have audit fields

Thus the new table layout is as follows

* User is the driving table.
* Useremails have a Many-To-One relationship with User. Each User has many user email combinations. Each user email combination has only one User.
* Roles have a Many-To-Many relationship with Users.

![Image of Database Layout](usersfinaldb.png)

The following endpoints represent what is new in this exception handling application!
