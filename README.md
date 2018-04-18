# Distributed-Microservices
This Distributed microservices project is developed with the following stack :
- Spring boot Config Server (as a cetralised cofiguration server for all the connected microservices)
- Eureka Server (as a microservices discovery server)
- Zuul Proxy Server (as a server side proxy to direct url implicitly)
- Zipkin Server (to trace web access log)
- DirectFlightConnectionServiceApplication class to set up and provide flight details.
- DirectFlightCostServiceApplication class to populate flight fare from mongodb.
- FlightAggregationFrontendApplication class as the frontend application which utilised data provided by DirectFlightConnectionServiceApplication and DirectFlightCostServiceApplication implicitly.

MongoDB is used as the data store for this application.
