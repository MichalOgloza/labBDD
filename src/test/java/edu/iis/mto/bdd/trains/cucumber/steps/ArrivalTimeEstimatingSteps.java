package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładającże;
import org.joda.time.LocalTime;

public class ArrivalTimeEstimatingSteps {

    @Zakładającże("^chcę się dostać z (.*) do (.*)$")
    public void givenStartAndDestination(String from, String to) {
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdża o (.*) na linii (.*)$")
    public void andTheNextTrainLeavesAtGivenTimeOnGivenLine(@Transform(JodaLocalTimeConverter.class) LocalTime departureTime, String givenLine) {
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenIAskedAboutArrivalTime() {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)$")
    public void thenIShouldBeInformedAboutFollowingArrivalTime(@Transform(JodaLocalTimeConverter.class) LocalTime arrivalTime) {
        throw new PendingException();
    }
}
