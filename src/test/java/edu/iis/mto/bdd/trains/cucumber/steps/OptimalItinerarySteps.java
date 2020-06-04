package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import org.hamcrest.Matchers;
import org.joda.time.LocalTime;

import java.util.List;

import static org.junit.Assert.assertThat;

public class OptimalItinerarySteps {

    private IntineraryService intineraryService;
    private List<LocalTime> got;

    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
                                    @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
        throw new PendingException();
    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
                                  @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        this.got = intineraryService.findNextDepartures(departure, destination, startTime);
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        this.got.retainAll(expectedTrainTimes);
        assertThat(this.got.size(), Matchers.equalTo(expectedTrainTimes.size()));
    }
}
