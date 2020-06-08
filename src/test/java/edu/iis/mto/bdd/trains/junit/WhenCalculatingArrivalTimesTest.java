package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.cucumber.steps.JodaLocalTimeConverter;
import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.StandardItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class WhenCalculatingArrivalTimesTest {
    private final int STANDARD_INTERVAL = 15;
    private final int EXTENDED_INTERVAL = 30;
    private final int NEGATIVE_INTERVAL = -1;

    @Mock
    private TimetableService timetableService;

    private Line testedLine;
    private JodaLocalTimeConverter converter;

    @Before
    public void setUp() throws Exception {
        testedLine = Line.named("Western").departingFrom("North Richmond");
        converter = new JodaLocalTimeConverter();
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
                .findNextDepartures("North Richmond", "Parramatta", converter.transform("08:29"));

        assertThrows(NullPointerException.class, invalidCall);
    }

    @Test
    public void ifTimetableServiceInjectedToItineraryServiceReturnedNullOrEmptyListOfArrivalTimesThenCallingFindNextDeparturesShouldEndUpWithNPE() {

    }

    @Test
    public void ifBetweenProvidedDepartureStationAndDestinationDoesNotExistAnyLineThenCallingFindNextDeparturesShouldEndUpIllegalArgumentExceptionThrown() {

    }

    @Test
    public void findArrivalTimesOfTimetableServiceShouldBeCalledOncePerFindNextDeparturesCall() {

    }
}
