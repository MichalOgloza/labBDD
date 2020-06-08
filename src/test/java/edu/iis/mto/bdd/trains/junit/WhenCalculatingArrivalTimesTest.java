package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.junit.Test;
import org.mockito.Mock;

public class WhenCalculatingArrivalTimesTest {
    private ItineraryService itineraryServiceToTest;

    @Mock
    private TimetableService timetableService;
    private Line testedLine;

    @Test
    public void passingNegativeIntervalToItineraryServiceShouldCauseIllegalArgumentException() {
        
    }

    @Test
    public void passingNullTimeTableServiceToItineraryServiceShouldEndUpWithNPEAfterCallingFindNextDepartures() {
        
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
