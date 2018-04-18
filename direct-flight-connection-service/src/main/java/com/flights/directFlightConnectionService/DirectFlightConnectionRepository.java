package com.flights.directFlightConnectionService;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "directFlightConnections", path = "direct-flight-connections2")
interface DirectFlightConnectionRepository extends MongoRepository<DirectFlightConnection, String> {
	
	Collection<DirectFlightConnection> findByAirline(@Param("airline") String airline);
}
