package com.flights.flightAggregationFrontend.mvc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flights.flightAggregationFrontend.client.DirectFlightConnectionServiceClient;
import com.flights.flightAggregationFrontend.client.DirectFlightCostServiceClient;
import com.flights.flightAggregationFrontend.client.DirectFlightCostServiceClientFallback;
import com.flights.flightAggregationFrontend.model.DirectFlightConnection;
import com.flights.flightAggregationFrontend.model.DirectFlightCost;
import com.flights.flightAggregationFrontend.model.UiAggregatedFlight;

@Controller
public class AggregatedFlightsViewController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AggregatedFlightsViewController.class);

	@Autowired
	private DirectFlightCostServiceClient directFlightCostServiceClient;

	@Autowired
	private DirectFlightCostServiceClientFallback directFlightCostServiceClientFallback;

	@Autowired
	private DirectFlightConnectionServiceClient directFlightConnectionServiceClient;

	@GetMapping("/")
	public String getAggregatedFlights(Model model) {
		LOGGER.info("Request detected");
		List<UiAggregatedFlight> aggregatedFlights = new ArrayList<>();
		LOGGER.info("******************************************************************************");
		LOGGER.info("******************************************************************************");
		LOGGER.info("******************************************************************************");
		for (DirectFlightConnection d: directFlightConnectionServiceClient.getDirectFlightConnections().getContent()) {
			LOGGER.info(d.getAirline().toString());
		}
		
		LOGGER.info("******************************************************************************");
		LOGGER.info("******************************************************************************");
		LOGGER.info("******************************************************************************");
		directFlightConnectionServiceClient.getDirectFlightConnections().getContent()
				.forEach((directFlightConnection) -> {

					DirectFlightCost flightCost = directFlightCostServiceClient
							.findByAirlineAndDepartureAirportAndArrivalAirport(directFlightConnection.getAirline(),
									directFlightConnection.getDepartureAirport(),
									directFlightConnection.getArrivalAirport())
							.getContent();

					directFlightCostServiceClientFallback.addDirectFlightCost(flightCost);

					aggregatedFlights
							.add(new UiAggregatedFlight(flightCost.getAirline(), flightCost.getDepartureAirport(),
									flightCost.getArrivalAirport(), directFlightConnection.getDepartureTime(),
									directFlightConnection.getArrivalTime(), flightCost.getCost()));

				});

		model.addAttribute("aggregatedFlights", aggregatedFlights);
		return "aggregated-flights";
	}
}
