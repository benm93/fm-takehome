# To Do:
- [X] Pull Route Data from API
- [X] Pull Stop data from API
- [X] Persist Data to DB
- [ ] Query DB to Answer questions
- [ ] Optimize
- [ ] Testing and Documentation

# Notes:

subway is route type 1

http://localhost:8080/h2-console
see logs to get jdbc connection string

how do i figure out how many stops are on a given route?
`curl -X GET "https://api-v3.mbta.com/stops?filter%5Broute%5D=751" -H "accept: application/vnd.api+json"`

what routes are associated with a given stop?
`curl -X GET "https://api-v3.mbta.com/routes?include=stop&filter%5Bstop%5D=1673" -H "accept: application/vnd.api+json"`