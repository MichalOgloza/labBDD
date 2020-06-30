package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

public class ArrivalTimeSteps
{
    @Zakładając("^chcę się dostać z \"(.*)\" do \"(.*)\"$")
    public void givenDestination(String start, String destination) {
        throw new PendingException();
    }

    @I("^następny pociąg odjeżdża o (.*) na linii \"(.*)\"$")
    public void andTrainArrives(@Transform(JodaLocalTimeConverter.class) LocalTime startTime, String line) {
        throw new PendingException();
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void whenAskAboutArrivalTime() {
        throw new PendingException();
    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas: (.*)$")
    public void thenShouldGetTime(@Transform(JodaLocalTimeConverter.class) LocalTime time) {
        throw new PendingException();
    }
}
