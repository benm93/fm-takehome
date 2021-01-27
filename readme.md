# To Do:
- [X] Pull Route Data from API
- [X] Pull Stop data from API
- [X] Persist Data to DB
- [X] Query DB to Answer questions
- [ ] Add some tests
- [ ] Optimize and Refactor
- [X] Documentation

# Notes:

Navigate to http://localhost:8080/h2-console to browse database.
The jdbc connection string is printed to the console during startup.

# Documentation

After starting the application, call the /fetch endpoint to load MBTA data into the local DB.

Then,

Call the /getSubwayRoutes endpoint to see the answer to Question 1.

Call the /getSubwayRouteMostStops endpoint to see the answer to Question 2.

Call the /getSubwayRouteFewestStops endpoint to see the answer to Question 3.

Call the /getSubwayStopsByMunicipality endpoint to see the answer for Question 5.