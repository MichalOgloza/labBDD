package edu.iis.mto.bdd.trains.utils;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.StandardItineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;

public class ItineraryServiceBuilder implements Builder<StandardItineraryService> {

    private int minutes;
    private TimetableService timeTableService;
    private Line trainLine;

    @Override
    public StandardItineraryService build() {
        return new StandardItineraryService(trainLine, timeTableService, minutes);
    }

    public ItineraryServiceBuilder ofLine(Line trainLine) {
        this.trainLine = trainLine;
        return this;
    }

    public ItineraryServiceBuilder withTimeTableService(TimetableService timeTableService) {
        this.timeTableService = timeTableService;
        return this;
    }

    public ItineraryServiceBuilder withInterval(int minutes) {
        this.minutes = minutes;
        return this;
    }
}
