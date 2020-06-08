package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.mockito.Mock;

public class WhenCalculatingArrivalTimesTest {
    private ItineraryService itineraryServiceToTest;

    @Mock
    private TimetableService timetableService;
    private Line testedLine;
    
}
