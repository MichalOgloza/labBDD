package edu.iis.mto.bdd.trains.utils;

import edu.iis.mto.bdd.trains.services.StandardItineraryService;

public class ItineraryServiceBuilder implements Builder<StandardItineraryService> {
    
    @Override
    public StandardItineraryService build() {
        return new StandardItineraryService();
    }
}
