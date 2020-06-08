package edu.iis.mto.bdd.trains.utils;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.StandardItineraryService;

public class ItineraryServiceBuilder implements Builder<StandardItineraryService> {

    @Override
    public StandardItineraryService build() {
        return new StandardItineraryService();
    }

    public ItineraryServiceBuilder ofLine(Line trainLine) {
        return this;
    }

    public ItineraryServiceBuilder withTimeTableService(InMemoryTimetableService timeTableService) {
        return this;
    }

    public ItineraryServiceBuilder withInterval(int i) {
        return this;
    }
}
