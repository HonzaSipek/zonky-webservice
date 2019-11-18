# Zonky webservice

Implement webservice (Java, Spring) which reads latest loans from Zonky marketplace, do some calculations and provides result on rest endpoint.
Number of fetched loans should be provided as request parameter (but there can be default value if not provided, e.q. 100).
Business logic has to find minimal interest rate, maximal interest rate and should calculate average interest rate, those values should be part of response body.

The public Zonky API is available here https://api.zonky.cz/loans/marketplace and here's the documentation 
http://docs.zonky.apiary.io/#.
