package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Guid implements IntineraryService {

    private TimetableService timetableService;
    // timeWindow in minutes
    private final int timeWindow;

    public Guid(TimetableService timetableService, int timeWindow) {
        this.timetableService = timetableService;
        this.timeWindow = timeWindow;
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        List<LocalTime> interestingDepartures = new ArrayList<>();
        List<LocalTime> allDepartures;
        for(Line line : lines) {
            allDepartures = timetableService.findArrivalTimes(line, departure);

            for(LocalTime time : allDepartures) {
                if(startTime.isBefore(time) && startTime.plusMinutes(this.timeWindow).isAfter(time)) {
                    interestingDepartures.add(time);
                }
            }

        }

        return interestingDepartures;
    }
}
