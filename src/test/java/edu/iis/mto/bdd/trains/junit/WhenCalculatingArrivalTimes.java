package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.cucumber.steps.JodaLocalTimeConverter;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhenCalculatingArrivalTimes {
	private static JodaLocalTimeConverter timeConverter;
	private static TimetableService timetableService;
	private static ItineraryService itineraryService;
	private static Line line;

	private static final String lineName = "Line0";
	private static final String lineStart = "Station0";
	private static final String[] stations = {lineStart, "Station1", "Station2", "Station3"};

	private final String departure = "Station1";
	private final String destination = "Station2";
	private List<LocalTime> departureTimes;
	private LocalTime startTime;
	private List<LocalTime> expectedTimes;

	@BeforeClass
	public static void init(){
		timeConverter = new JodaLocalTimeConverter();
		timetableService = mock(TimetableService.class);
		line = Line.named(lineName).departingFrom(lineStart).withStations(stations);
		itineraryService = new ItineraryService(line, timetableService);
	}

	@Before
	public void setup(){
		departureTimes = createTimes("9:56", "10:00", "10:04", "10:08", "10:12", "10:16");
		startTime = timeConverter.transform("10:00");
		expectedTimes = createTimes("10:04", "10:08", "10:12");
	}

	@Test
	public void shouldReturnProperListOfArrivalTimes(){
		when(timetableService.findLinesThrough(departure, destination)).thenReturn(Collections.singletonList(line));
		when(timetableService.findArrivalTimes(line, departure)).thenReturn(departureTimes);

		List<LocalTime> results = itineraryService.findNextDepartures(departure, destination, startTime);

		Assert.assertArrayEquals(expectedTimes.toArray(), results.toArray());
	}

	@Test
	public void shouldReturnEmptyListOfArrivalTimes(){
		when(timetableService.findLinesThrough(departure, destination)).thenReturn(Collections.singletonList(line));
		when(timetableService.findArrivalTimes(line, departure)).thenReturn(createTimes("9:45", "10:16", "10:30"));

		List<LocalTime> results = itineraryService.findNextDepartures(departure, destination, startTime);

		Assert.assertTrue(results.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException() {
		Line localLine = Line.named("Line1").departingFrom(lineStart);
		when(timetableService.findLinesThrough(departure, destination)).thenReturn(Collections.singletonList(localLine));

		itineraryService.findNextDepartures(departure, destination, startTime);
	}

	private List<LocalTime> createTimes(String ...times){
		List<LocalTime> localTimes = new ArrayList<>();
		for (String time : times) {
			localTimes.add(timeConverter.transform(time));
		}
		return localTimes;
	}
}
