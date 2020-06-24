package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.Guid;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class WhenCalculatingArrivalTimes {

    private static TimetableService mockTimetableService;
    private static IntineraryService testedIntineraryService;
    private static Line line;
    private static String departureFrom = "Parramatta", destination = "Town Hall";

    @BeforeClass
    public static void init() {
        mockTimetableService = Mockito.mock(TimetableService.class);
        line = Line.named("test line").departingFrom(departureFrom).withStations(departureFrom, destination);
        when(mockTimetableService.findLinesThrough(departureFrom, destination)).thenReturn(Arrays.asList(line));
    }

    @Test
    public void shouldReturnEmptyListIfDeparturesAreOutside15MinutesTimeWindow() {
        List<LocalTime> departuresTimes = Arrays.asList(
                new LocalTime(7, 0),
                new LocalTime(7, 59),
                new LocalTime(8, 0),
                new LocalTime(8, 15),
                new LocalTime(8, 16),
                new LocalTime(9, 0)
        );
        int timeWindow = 15;

        when(mockTimetableService.findArrivalTimes(line, departureFrom)).thenReturn(departuresTimes);

        testedIntineraryService = new Guid(mockTimetableService, timeWindow);

        List<LocalTime> expected = new ArrayList<>();
        assertThat(testedIntineraryService.findNextDepartures(departureFrom, destination, new LocalTime(8, 0)), Matchers.equalTo(expected));
    }

    @Test
    public void shouldReturnEmptyWhenTimeWindowIs0() {
        List<LocalTime> departuresTimes = Arrays.asList(
                new LocalTime(7, 0),
                new LocalTime(7, 59),
                new LocalTime(8, 0),
                new LocalTime(8, 1),
                new LocalTime(9, 0)
        );
        int timeWindow = 0;

        when(mockTimetableService.findArrivalTimes(line, departureFrom)).thenReturn(departuresTimes);
        IntineraryService intin = new Guid(mockTimetableService, timeWindow);

        List<LocalTime> expected = new ArrayList<>();
        assertThat(intin.findNextDepartures(departureFrom, destination, new LocalTime(8, 0)), Matchers.equalTo(expected));
    }

    @Test
    public void shouldReturnExactlyOneValueWhenOneValueMatchTimeWindow() {
        List<LocalTime> departuresTimes = Arrays.asList(
                new LocalTime(7, 0),
                new LocalTime(7, 59),
                new LocalTime(8, 0),
                new LocalTime(8, 5),
                new LocalTime(8, 10),
                new LocalTime(9, 0)
        );
        int timeWindow = 10;

        when(mockTimetableService.findArrivalTimes(line, departureFrom)).thenReturn(departuresTimes);
        IntineraryService intin = new Guid(mockTimetableService, timeWindow);

        List<LocalTime> expected = Arrays.asList(new LocalTime(8, 5));
        assertThat(intin.findNextDepartures(departureFrom, destination, new LocalTime(8, 0)), Matchers.equalTo(expected));
    }

    @Test
    public void shouldReturnAllValuesMatchingTimeWindow() {
        List<LocalTime> departuresTimes = Arrays.asList(
                new LocalTime(7, 0),
                new LocalTime(7, 59),
                new LocalTime(8, 0),
                new LocalTime(8, 5),
                new LocalTime(8, 6),
                new LocalTime(8, 7),
                new LocalTime(8, 10),
                new LocalTime(9, 0)
        );
        int timeWindow = 10;

        when(mockTimetableService.findArrivalTimes(line, departureFrom)).thenReturn(departuresTimes);
        IntineraryService intin = new Guid(mockTimetableService, timeWindow);

        List<LocalTime> expected = Arrays.asList(new LocalTime(8, 5), new LocalTime(8, 6), new LocalTime(8, 7));
        assertThat(intin.findNextDepartures(departureFrom, destination, new LocalTime(8, 0)), Matchers.equalTo(expected));
    }

    @After
    public void dealocate() {
        testedIntineraryService = null;
    }

}

