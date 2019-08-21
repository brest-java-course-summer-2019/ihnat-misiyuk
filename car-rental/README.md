[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/ihnat-misiyuk.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/ihnat-misiyuk)
[![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/ihnat-misiyuk/badge.svg?branch=master)](https://coveralls.io/github/brest-java-course-summer-2019/ihnat-misiyuk?branch=master)

#  Car Repair Service Application #
Demo project for Car rental management with two entities: “Car” and “Rental”, related as one to many.
##  Prerequisites ##
jdk11<br/>
maven3+<br/>
##  Installing ##
> mvn clean install
##  Running with tests ##
> mvn clean test
### Server test ###
For server test jetty plugin can be used
```
mvn jetty:run 
```
Open http://localhost:8080
## REST Server ##
Start REST app:
```
cd rest-app
mvn jetty:run
```
### Try CURL requests ###
Get all cars
```
curl -v localhost:8088/cars 
```
Create new car via REST:
```
curl -H "Content-Type: application/json" -X POST -d '{"carId":null,"carBrand":"xyz", "carYear":"0000", "carGearbox":"something", "carEngine":"interesting", "carClass":"super"}' -v localhost:8088/department 
```