# Zonky webservice
## Assignment

Implement webservice (Java, Spring) which reads latest loans from Zonky marketplace, do some calculations and provides result on rest endpoint.
Number of fetched loans should be provided as request parameter (but there can be default value if not provided, e.q. 100).
Business logic has to find minimal interest rate, maximal interest rate and should calculate average interest rate, those values should be part of response body.

The public Zonky API is available here https://api.zonky.cz/loans/marketplace and here's the documentation 
http://docs.zonky.apiary.io/#.

## Requirements
* Java version min. 1.8
* Maven version min. 3.6.1

## How to run unit tests
`mvn test`

## How to run integration tests
`mvn verify -P integration`

## How to build
`mvn clean package`

## How to run service on localhost
`java -jar [path_to_jar_file]`

## How to send request
The loan endpoint is available on `localhost:8080/api/loans` and provides method to get last loans from Zonky marketplace with statistics (min, max, avg of interest rate).
You can use request parameter `count` to specify how many loans you want to get (is not required, default value is 100).

For example:
`GET localhost:8080/api/loans?count=5`

