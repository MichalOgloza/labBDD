package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

public class EstimatedArrivalTimeSteps {

	@Zakładając("^chcę się dostać z \"(.*)\" do \"(.*)\"")
	public void givenDepartureAndDestination(String departure, String destination) {
		throw new PendingException();
	}

	@I("^następny pociąg odjeżdża o (.*) na linii \"(.*)\"")
	public void andNextTrainLeaves(@Transform(JodaLocalTimeConverter.class) LocalTime leavingTime, String line){
		throw new PendingException();
	}

	@Gdy("^zapytam o godzinę przyjazdu")
	public void whenIAsk(){
		throw new PendingException();
	}

	@Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
	public void shouldGetEstimatedArrivalTime(@Transform(JodaLocalTimeConverter.class) LocalTime estimatedArrivalTime){
		throw new PendingException();
	}

}
