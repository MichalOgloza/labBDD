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
}
