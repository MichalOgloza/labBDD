package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.cucumber.steps.JodaLocalTimeConverter;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.StandardItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WhenCalculatingArrivalTimesTest {

    private static final String THIS_PLACE_DOES_NOT_EXIST_TOO = "ThisPlaceDoesNotExistToo";
    public static final String EXAMPLE_TIME = "08:29";
    private final int STANDARD_INTERVAL = 15;
    private final int EXTENDED_INTERVAL = 30;
    private final int NEGATIVE_INTERVAL = -1;
    private final String THIS_PLACE_DOES_NOT_EXIST = "ThisPlaceDoesNotExists";

    private TimetableService timetableService;

    private Line testedLine;
    private JodaLocalTimeConverter converter;

    @Before
    public void setUp() throws Exception {
        testedLine = Line.named("Western").departingFrom("North Richmond");
        converter = new JodaLocalTimeConverter();
        timetableService = Mockito.mock(TimetableService.class);
    }

    @Test
    public void passingNegativeIntervalToItineraryServiceShouldCauseIllegalArgumentException() {
        var builder = StandardItineraryService.builder().withInterval(NEGATIVE_INTERVAL);
        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    public void passingNullTimeTableServiceToItineraryServiceShouldEndUpWithNPEAfterCallingFindNextDepartures() {
        var itineraryService = StandardItineraryService.builder()
                .withInterval(STANDARD_INTERVAL)
                .withTimeTableService(null)
                .ofLine(testedLine).build();

        Executable invalidCall = () -> itineraryService
                .findNextDepartures("North Richmond", "Parramatta", converter.transform(EXAMPLE_TIME));

        assertThrows(NullPointerException.class, invalidCall);
    }

    @Test
    public void ifTimetableServiceInjectedToItineraryServiceReturnedNullListOfArrivalTimesThenCallingFindNextDeparturesShouldEndUpWithNPE() {
        when(timetableService.findArrivalTimes(Mockito.any(Line.class), Mockito.any())).thenReturn(null);
        when(timetableService.findLinesThrough(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.singletonList(testedLine));
        var itineraryService = StandardItineraryService.builder()
                .withInterval(STANDARD_INTERVAL)
                .withTimeTableService(timetableService)
                .ofLine(testedLine).build();

        Executable invalidCall = () -> itineraryService
                .findNextDepartures("North Richmond", "Parramatta", converter.transform(EXAMPLE_TIME));

        assertThrows(NullPointerException.class, invalidCall);
    }

    @Test
    public void ifBetweenProvidedDepartureStationAndDestinationDoesNotExistAnyLineThenCallingFindNextDeparturesShouldEndUpIllegalArgumentExceptionThrown() {
        when(timetableService.findArrivalTimes(Mockito.any(Line.class), Mockito.any())).thenReturn(exampleArrivalTimes());
        var itineraryService = StandardItineraryService.builder()
                .withInterval(STANDARD_INTERVAL)
                .withTimeTableService(timetableService)
                .ofLine(testedLine).build();

        Executable invalidCall = () -> itineraryService
                .findNextDepartures(THIS_PLACE_DOES_NOT_EXIST, THIS_PLACE_DOES_NOT_EXIST_TOO, converter.transform(EXAMPLE_TIME));

        assertThrows(IllegalArgumentException.class, invalidCall);
    }

    @Test
    public void findArrivalTimesOfTimetableServiceShouldBeCalledOncePerFindNextDeparturesCall() {
        when(timetableService.findLinesThrough(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.singletonList(testedLine));
        when(timetableService.findArrivalTimes(Mockito.any(Line.class), Mockito.any())).thenReturn(exampleArrivalTimes());
        var itineraryService = StandardItineraryService.builder()
                .withInterval(STANDARD_INTERVAL)
                .withTimeTableService(timetableService)

                .ofLine(testedLine).build();
        itineraryService.findNextDepartures(THIS_PLACE_DOES_NOT_EXIST, THIS_PLACE_DOES_NOT_EXIST_TOO, converter.transform(EXAMPLE_TIME));
        verify(timetableService, times(1)).findArrivalTimes(testedLine, THIS_PLACE_DOES_NOT_EXIST_TOO);
    }

    private List<LocalTime> exampleArrivalTimes(){
        return new ArrayList<>(Arrays.asList(converter.transform("08:23"), converter.transform("08:26")));
    }
}
