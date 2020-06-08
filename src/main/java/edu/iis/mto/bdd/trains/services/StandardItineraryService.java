package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.utils.ItineraryServiceBuilder;
import org.joda.time.LocalTime;

import java.util.List;

public class StandardItineraryService implements ItineraryService {
    public static ItineraryServiceBuilder builder() {
        return new ItineraryServiceBuilder();
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        return null;
    }
}
