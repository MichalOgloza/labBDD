package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.StandardItineraryService;
import org.joda.time.LocalTime;

import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptimalItinerarySteps {

    private List<LocalTime> arrivalTimes;
    private ItineraryService itineraryService;

    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {

        var trainLine = Line.named(line).departingFrom(lineStart);
        var timeTableService = new InMemoryTimetableService();
        itineraryService = StandardItineraryService.builder()
                .ofLine(trainLine)
                .withTimeTableService(timeTableService)
                .withInterval(15)
                .build();

        //is context working?
        var computedDepartureTimes = timeTableService.findArrivalTimes(trainLine, departure);
        assertThat(computedDepartureTimes, is(equalTo(departureTimes)));
        var linesCollection = timeTableService.findLinesThrough(departure, destination);
        assertThat(linesCollection, hasItem(trainLine));
    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        arrivalTimes = itineraryService.findNextDepartures(departure, destination, startTime);
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        assertThat(arrivalTimes, is(equalTo(expectedTrainTimes)));
    }
}
