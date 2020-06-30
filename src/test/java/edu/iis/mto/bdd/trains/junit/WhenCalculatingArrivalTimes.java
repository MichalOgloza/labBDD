package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.DefaultIntineraryService;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class WhenCalculatingArrivalTimes {

    private static final List<LocalTime> EMPTY_LIST = new ArrayList<>();
    private static final String startStation = "Epping";
    private static final String endStation = "Central";
    private static final LocalTime startTime = new LocalTime(16, 20);

    private IntineraryService intineraryService;
    private TimetableService timetableServiceMock;
    private Line line;

    @Before
    public void init()
    {
        line = Line.named("example").departingFrom(startStation);
        timetableServiceMock = Mockito.mock(TimetableService.class);
        intineraryService = new DefaultIntineraryService(timetableServiceMock);
        when(timetableServiceMock.findLinesThrough(startStation, endStation)).thenReturn(new ArrayList<>(List.of(line)));
    }

    @Test
    public void WhenThereAreNoAvailableDeparturesItShouldReturnEmptyList()
    {
        when(timetableServiceMock.findArrivalTimes(line, startStation)).thenReturn(EMPTY_LIST);
        assertThat(intineraryService.findNextDepartures(startStation, endStation, startTime), is(equalTo(EMPTY_LIST)));
    }

    @Test
    public void WhenAllDeparturesLeftItShouldReturnEmptyList()
    {
        List<LocalTime> departures = new ArrayList<>();
        departures.add(new LocalTime(9, 30));
        departures.add(new LocalTime(12, 50));
        departures.add(new LocalTime(14, 3));
        departures.add(new LocalTime(16, 10));
        when(timetableServiceMock.findArrivalTimes(line, startStation)).thenReturn(departures);
        assertThat(intineraryService.findNextDepartures(startStation, endStation, startTime), is(equalTo(EMPTY_LIST)));
    }

    @Test
    public void WhenOneDepartureAvailableItShouldBeReturned()
    {
        List<LocalTime> departures = new ArrayList<>();
        departures.add(new LocalTime(9, 30));
        departures.add(new LocalTime(12, 50));
        departures.add(new LocalTime(14, 3));
        departures.add(new LocalTime(16, 30));
        when(timetableServiceMock.findArrivalTimes(line, startStation)).thenReturn(departures);

        List<LocalTime> result = new ArrayList<>(List.of(new LocalTime(16, 30)));
        assertThat(intineraryService.findNextDepartures(startStation, endStation, startTime), is(equalTo(result)));
    }

    @Test
    public void EveryAvailableDeparturesShouldBeReturned()
    {
        List<LocalTime> departures = new ArrayList<>();
        departures.add(new LocalTime(16, 22));
        departures.add(new LocalTime(16, 25));
        departures.add(new LocalTime(16, 30));
        departures.add(new LocalTime(16, 34));
        when(timetableServiceMock.findArrivalTimes(line, startStation)).thenReturn(departures);
        assertThat(intineraryService.findNextDepartures(startStation, endStation, startTime), is(equalTo(departures)));
    }

    @Test
    public void Departures15MinutesOrMoreAfterStartTimeShouldNotBeReturned()
    {
        List<LocalTime> departures = new ArrayList<>();
        departures.add(new LocalTime(16, 25));
        departures.add(new LocalTime(16, 35));
        departures.add(new LocalTime(17, 25));
        when(timetableServiceMock.findArrivalTimes(line, startStation)).thenReturn(departures);

        List<LocalTime> result = new ArrayList<>(List.of(new LocalTime(16, 25)));
        assertThat(intineraryService.findNextDepartures(startStation, endStation, startTime), is(equalTo(result)));
    }
}
