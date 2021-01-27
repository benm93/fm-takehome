# To Do:

- [X] Pull Route Data from API
- [X] Pull Stop data from API
- [X] Persist Data to DB
- [X] Query DB to Answer questions
- [X] Add more tests
- [X] Optimize and Refactor
- [X] Documentation

# Notes:

Data is pulled from the MBTA API automatically during application startup.
This data is stored in a local H2 database.
Navigate to http://localhost:8080/h2-console to browse database.
The jdbc connection string is printed to the console during startup.

# Documentation:

After startup, the application will be listening at localhost:8080

Call the /getSubwayRoutes endpoint to see the answer to Question 1.

Call the /getSubwayRouteMostStops endpoint to see the answer to Question 2.

Call the /getSubwayRouteFewestStops endpoint to see the answer to Question 3.

Call the /getSubwayStopsByMunicipality?town=*town_name* endpoint to see the answer for Question 5.
For example `localhost:8080/getSubwayStopsByMunicipality?town=Somerville`