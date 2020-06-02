package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.Arrays;
import java.util.List;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;

import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.junit.Assert;

public class OptimalItinerarySteps {
	private List<LocalTime> trainTimes;
	private ItineraryService itineraryService;
	private TimetableService timetableService = new InMemoryTimetableService();

    @Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
		Line currentLine = Line.named(line).departingFrom(lineStart);
		List<Line> lines = timetableService.findLinesThrough(departure, destination);
		Assert.assertTrue(lines.contains(currentLine));
		trainTimes = timetableService.findArrivalTimes(currentLine, departure);
		Assert.assertArrayEquals(trainTimes.toArray(), departureTimes.toArray());
		itineraryService = new ItineraryService(currentLine, timetableService);
    }

    @Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
    public void whenIWantToTravel(String departure, String destination,
            @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
        trainTimes = itineraryService.findNextDepartures(departure, destination, startTime);
    }

    @Wtedy("^powinienem uzyskać informację o pociągach o:$")
    public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
		Assert.assertArrayEquals(expectedTrainTimes.toArray(), trainTimes.toArray());
    }
}
