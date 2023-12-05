# Simple Calculator

Project in Spring Boot + Angular

## Web links

Repo: [https://github.com/jackie-d/simple-calculator](https://github.com/jackie-d/simple-calculator)

Online Web-app: [http://calc.eu-north-1.elasticbeanstalk.com/](https://calc.eu-north-1.elasticbeanstalk.com/)

## Setup Instructions

Setup for development is automatic on run

```

```

- *Requires Node v. 16 or less*

## Run Instructions

```
mvn clean install
cd backend
mvn spring-boot:run
```

__Only frontend (with live reload):__

```
cd frontend/src/main/simplecalculator-ng
ng serve
```

## Build Instructions

```
mvn package
```

the _tomcat compatible_ produced `.war` file app will be found in the 
`backend/target/` folder.

*Before build:* on the front-end environment file replace the API endpoint with your production server: 
`frontend\src\main\simplecalculator-ng\src\environments` on the `endpointUrl` variable - keeping the `/api/v1/` path on your domain


## Test Instructions

__For backend:__

Junit and spring boot test

```
cd backend
mvn test
```

__For frontend:__

Karma test suite for angular

```
cd fronted/src/main/simplecalculator-ng
ng test
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
the data for the history of the calculations to provide to review.

__Additional features:__

- Tests for backend (TDD)
- Sample tests for frontend (unit basics)
- Simple client/server authorization with header token
- Support for consequential calculations, negative numbers, calculations with zero
- Responsive UI

__Todo:__

- API [limiting](https://github.com/shlomokoren/spring-boot-throttling) for HTTP requests enforce


_© Year 2021 - Jackie degli Innocenti_