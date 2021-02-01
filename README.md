# Simple Calculator

Project in Spring Boot + Angular

## Web links

Repo:

Hosting: 

## Setup Instructions

Setup for development is automatic on run

```

```

## Run Instructions

```
mvn clean install
cd backend
mvn spring-boot:run
```

## Notes

For the purpose of this exercise it has been realized a project structure using 
the Maven capability of connecting and organize the folders in submodules, having
the backend and frontend submodules, other than the package container module.

The backend is realised in Spring Boot technology, allowing to put in place a 
 REST API framework that implements the handling and managing of the requests and
dispatching to the following components as like as business logic controller and 
service to store the data and then to provide the response ready to the frontend.

The frontend is a web-app in Angular that contains the user interface for the web
and shows the calculator to the user and allows to compose the equation and to 
receive the solution. Moreover it has the feature to obtain and show to the user 
all the past equations composed with the result.

Once composed the equations on the interface and pressed the equal sign the result 
is requested to compute to the backend and immediately shown. The idea behind 
the show of the the previous results is to made the user possible to navigate 
through the totality of it and so obtain the history of the calculations to review.

The backend has a strong architecture with SOLID and DRY pattern applied, a 
complete test suite with good coverage and and rely on a H2 database to store 
the data for the history of the calculation to provide to review.



_© Year 2021 - Jackie degli Innocenti_