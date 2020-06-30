package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class DefaultIntineraryService implements IntineraryService {

    private TimetableService timetableService;
    private static final int TIME_LIMIT = 15;

    public DefaultIntineraryService(TimetableService timetableService)
    {
        this.timetableService = timetableService;
    }

    @Override
    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {

        List<LocalTime> result = new ArrayList<>();

        for(Line line : timetableService.findLinesThrough(departure, destination))
        {
            for(LocalTime time : timetableService.findArrivalTimes(line, departure))
            {
                LocalTime endTime = startTime.plusMinutes(TIME_LIMIT);
                if(startTime.isBefore(time) && time.isBefore(endTime))
                {
                    result.add(time);
                }
            }
        }
        return result;
    }
}
