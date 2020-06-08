package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.utils.ItineraryServiceBuilder;
import org.joda.time.LocalTime;

import java.util.List;

public class StandardItineraryService implements ItineraryService {

    private final Line trainLine;
    private final TimetableService timeTableService;
    private final int minutes;

    public StandardItineraryService(Line trainLine, TimetableService timeTableService, int minutes) {
        this.trainLine = trainLine;
        this.timeTableService = timeTableService;
        this.minutes = minutes;
    }

    public static ItineraryServiceBuilder builder() {
        return new ItineraryServiceBuilder();
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        return null;
    }
}
