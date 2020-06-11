package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.utils.ItineraryServiceBuilder;
import org.joda.time.LocalTime;

import java.util.List;
import java.util.stream.Collectors;

public class StandardItineraryService implements ItineraryService {

    private final Line trainLine;
    private final TimetableService timeTableService;
    private final int minutes;

    public StandardItineraryService(Line trainLine, TimetableService timeTableService, int minutes) {
        this.trainLine = trainLine;
        this.timeTableService = timeTableService;
        if(minutes < 0) throw new IllegalArgumentException("Interval should be at least greater than or equal to 0, but got: " + minutes);
        this.minutes = minutes;
    }

    public static ItineraryServiceBuilder builder() {
        return new ItineraryServiceBuilder();
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        var lines = timeTableService.findLinesThrough(departure, destination);
        if(!lines.contains(trainLine))
            throw new IllegalArgumentException("Cannot prepare train's line itinerary for provided destination and departure station");

        var arrivalTimes = timeTableService.findArrivalTimes(trainLine, destination);
        return arrivalTimes.stream().filter(localTime -> startTime.compareTo(localTime) <= 0 && startTime.plusMinutes(minutes).compareTo(localTime) >= 0).collect(Collectors.toList());
    }
}
